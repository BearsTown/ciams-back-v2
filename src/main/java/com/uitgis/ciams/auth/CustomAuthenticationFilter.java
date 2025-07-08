package com.uitgis.ciams.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uitgis.ciams.dto.CiamsSsoUserDto;
import com.uitgis.ciams.mapper.CiamsLoginLogMapper;
import com.uitgis.ciams.mapper.CiamsSsoUserMapper;
import com.uitgis.ciams.util.JsonUtil;
import com.uitgis.ciams.util.ValidUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

	private final CiamsSsoUserMapper ciamsSsoUserMapper;
	private final CiamsLoginLogMapper ciamsLoginlogMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String grantType = request.getParameter("grant_type");

		if (ValidUtil.notEmpty(grantType)) {
			if (response.getStatus() == HttpStatus.OK.value()) {
			}
		} else {
			String url = request.getRequestURI();
			//refresh_token 체크
			if (url.contains("introspect")) {
				String token = request.getParameter("token");
				String username = getUsernameFromToken(token);

				CiamsSsoUserDto.Data user = ciamsSsoUserMapper.selectById(username);
				if (user != null && user.getLock()) {
					throw new OAuth2AuthenticationException("User account is locked");
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	private static String getUsernameFromToken(String token) throws JsonProcessingException {
		try {
			String[] chunks = token.split("\\.");
			Base64.Decoder decoder = Base64.getUrlDecoder();
			String body = new String(decoder.decode(chunks[1]));

			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HashMap<String, String> map = objectMapper.readValue(body, HashMap.class);

			return map.get("sub"); // OAuth2에서는 user_name 대신 sub 사용
		} catch (JsonProcessingException e) {
			throw new JsonProcessingException("Failed to parse JWT token") {};
		}
	}
}
