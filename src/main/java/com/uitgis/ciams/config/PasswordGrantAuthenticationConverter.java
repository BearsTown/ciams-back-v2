package com.uitgis.ciams.config;

import com.uitgis.ciams.config.PasswordGrantAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PasswordGrantAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter("grant_type");
        if (!"password".equals(grantType)) {
            return null;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String clientId = request.getParameter("client_id");  // client_id 파라미터 추가

        Set<String> scopes = Collections.emptySet();
        if (StringUtils.hasText(request.getParameter("scope"))) {
            scopes = new HashSet<>(Arrays.asList(request.getParameter("scope").split(" ")));
        }

        Map<String, Object> additionalParameters = new HashMap<>();
        request.getParameterMap().forEach((key, value) -> {
            if (!key.equals("grant_type") && !key.equals("username") &&
                    !key.equals("password") && !key.equals("scope") &&
                    !key.equals("client_id")) {  // client_id 제외
                additionalParameters.put(key, value[0]);
            }
        });

        return new PasswordGrantAuthenticationToken(
                username,
                password,
                clientId,  // clientId 추가
                scopes,
                additionalParameters
        );
    }
}
