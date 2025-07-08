package com.uitgis.ciams.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

    	 response.setContentType("application/json");
         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

         // 예외 메시지 설정
         Map<String, Object> body = new HashMap<>();
         body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
         body.put("error", "Unauthorized");
         body.put("message", authException.getMessage());
         body.put("error_description", authException.getMessage());
         body.put("path", request.getServletPath());

         // JSON 변환 후 응답
         response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
