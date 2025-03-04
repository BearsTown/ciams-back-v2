package com.uitgis.ciams.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class PasswordGrantAuthenticationToken extends AbstractAuthenticationToken {
    private final String username;
    private final String password;
    private final String clientId;  // 추가
    private final Set<String> scopes;
    private final Map<String, Object> additionalParameters;


    public PasswordGrantAuthenticationToken(
            String username,
            String password,
            String clientId,  // 생성자 매개변수 추가
            Set<String> scopes,
            Map<String, Object> additionalParameters) {
        super(Collections.emptyList());
        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.scopes = scopes;
        this.additionalParameters = additionalParameters;
        setAuthenticated(false);
    }

    public String getClientId() {  // getter 추가
        return this.clientId;
    }


    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    public Set<String> getScopes() {
        return this.scopes;
    }

    public Map<String, Object> getAdditionalParameters() {
        return this.additionalParameters;
    }
}
