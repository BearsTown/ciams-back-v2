package com.uitgis.ciams.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uitgis.ciams.dto.CiamsLoginLogDto;
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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomFilter extends OncePerRequestFilter {

	private final CiamsSsoUserMapper ciamsSsoUserMapper;
	private final CiamsLoginLogMapper ciamsLoginlogMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String grantType = request.getParameter("grant_type");

		if (ValidUtil.notEmpty(grantType)) {
			if ("password".equals(grantType)) {
				String username = request.getParameter("username");
				addLoginHistory(username, request.getRemoteAddr());
				ciamsSsoUserMapper.initLoginFailCnt(username);
			} else if ("refresh_token".equals(grantType)) {
				String token = request.getParameter("refresh_token");
//				String username = getUsernameFromToken(token);
//				ciamsSsoUserMapper.initLoginFailCnt(username);
			}
		} else {
			String url = request.getRequestURI();
//			if (url.contains("check_token")) {
			if (url.contains("introspect")) {
				String token = request.getParameter("token");
				String username = getUsernameFromToken(token);

				CiamsSsoUserDto.Data user = ciamsSsoUserMapper.selectById(username);
				if (user != null && user.getLock()) {
					log.error("User account is locked: {}", username);
					response.setStatus(HttpStatus.FORBIDDEN.value());
					response.getWriter().write("User account is locked");
					return;
				}

				ciamsSsoUserMapper.initLoginFailCnt(username);
			}
		}

		// Default response for unauthenticated access
//		response.setStatus(HttpStatus.UNAUTHORIZED.value());
//		response.getWriter().write("Unauthorized access");

		filterChain.doFilter(request, response);
	}

	private static String getUsernameFromToken(String token) throws JsonProcessingException {
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String body = new String(decoder.decode(chunks[1]));

		ObjectMapper objectMapper = JsonUtil.getObjectMapper();
		HashMap<String, String> map = objectMapper.readValue(body, HashMap.class);

//		return map.get("user_name");
		return map.get("sub");
	}

	private void addLoginHistory(String username, String ip) {
		CiamsLoginLogDto.Add add = CiamsLoginLogDto.Add.builder()
				.loginId(username)
				.ip(ip)
				.build();

		ciamsLoginlogMapper.insert(add);
	}
}
