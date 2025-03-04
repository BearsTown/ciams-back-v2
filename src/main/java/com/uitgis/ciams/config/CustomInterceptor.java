package com.uitgis.ciams.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uitgis.ciams.dto.CiamsLoginLogDto;
import com.uitgis.ciams.dto.CiamsSsoUserDto;
import com.uitgis.ciams.mapper.CiamsLoginLogMapper;
import com.uitgis.ciams.mapper.CiamsSsoUserMapper;
import com.uitgis.ciams.util.JsonUtil;
import com.uitgis.ciams.util.ValidUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomInterceptor implements HandlerInterceptor {

	private final CiamsSsoUserMapper ciamsSsoUserMapper;
	private final CiamsLoginLogMapper ciamsLoginlogMapper;

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		String grantType = request.getParameter("grant_type");

		if (ValidUtil.notEmpty(grantType)) {

			if (response.getStatus() == HttpStatus.OK.value() ) {

				if (grantType.equals("password")) {
					String id = request.getParameter("username");
					addLoginHistory(id, request.getRemoteAddr());
					ciamsSsoUserMapper.initLoginFailCnt(id);
				}
				if (grantType.equals("refresh_token")) {
					String token = request.getParameter("refresh_token");
					String username = getUsernameFromToken(token);

					ciamsSsoUserMapper.initLoginFailCnt(username);
				}
			}
		}else{
			String url = request.getRequestURI();
			if(url.contains("check_token") && response.getStatus() == HttpStatus.OK.value()){
				String token = request.getParameter("token");
				String username = getUsernameFromToken(token);

				CiamsSsoUserDto.Data user = ciamsSsoUserMapper.selectById(username);
				if(user != null && user.getLock()){
					throw new OAuth2AuthenticationException("User account is locked");
				}

				ciamsSsoUserMapper.initLoginFailCnt(username);
			}
		}
	}

	/**
	 * 사용자 refresh_token 체크시 postHandle 타지 않아서 추가
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
    	String grantType = request.getParameter("grant_type");

    	if (ValidUtil.notEmpty(grantType) && grantType.equals("refresh_token")) {
			String token = request.getParameter("refresh_token");
			String username = getUsernameFromToken(token);
			ciamsSsoUserMapper.initLoginFailCnt(username);
		}
	}

	private static String getUsernameFromToken(String token) throws JsonProcessingException {


		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String body = new String(decoder.decode(chunks[1]));

		ObjectMapper objectMapper = JsonUtil.getObjectMapper();
		HashMap<String, String> map = objectMapper.readValue(body, HashMap.class);

		return map.get("user_name");
	}

	private void addLoginHistory(String username, String ip) {
		CiamsLoginLogDto.Add add = CiamsLoginLogDto.Add.builder()
				.loginId(username)
				.ip(ip)
				.build();

		ciamsLoginlogMapper.insert(add);
	}
}
