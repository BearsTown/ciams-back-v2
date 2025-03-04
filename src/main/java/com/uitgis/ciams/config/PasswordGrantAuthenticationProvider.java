package com.uitgis.ciams.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.util.HashMap;
import java.util.Map;

public class PasswordGrantAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationManager authenticationManager;
    private final OAuth2TokenGenerator<?> tokenGenerator;
    private final RegisteredClientRepository registeredClientRepository;  // 추가

    public PasswordGrantAuthenticationProvider(
            AuthenticationManager authenticationManager,
            OAuth2TokenGenerator<?> tokenGenerator,
            RegisteredClientRepository registeredClientRepository) {  // 생성자 수정
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws OAuth2AuthenticationException {
        PasswordGrantAuthenticationToken passwordGrant =
                (PasswordGrantAuthenticationToken) authentication;

        // clientId로 RegisteredClient 조회
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(passwordGrant.getClientId());
        if (registeredClient == null) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
        }

        UsernamePasswordAuthenticationToken usernamePasswordToken =
                new UsernamePasswordAuthenticationToken(
                        passwordGrant.getPrincipal(),
                        passwordGrant.getCredentials()
                );

        Authentication usernamePasswordAuthentication =
                authenticationManager.authenticate(usernamePasswordToken);

        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(usernamePasswordAuthentication)
                .authorizationGrantType(new AuthorizationGrantType("password"))
                .authorizedScopes(passwordGrant.getScopes());

        OAuth2TokenContext tokenContext = tokenContextBuilder.build();
        OAuth2Token generatedAccessToken = tokenGenerator.generate(tokenContext);

        if (generatedAccessToken == null) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.SERVER_ERROR);
        }

        OAuth2AccessToken accessToken = null;
        if (generatedAccessToken instanceof OAuth2AccessToken) {
            accessToken = (OAuth2AccessToken) generatedAccessToken;
        }

        // Refresh Token 생성
        OAuth2RefreshToken refreshToken = null;
        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN)) {
            OAuth2TokenContext refreshTokenContext = DefaultOAuth2TokenContext.builder()
                    .registeredClient(registeredClient)
                    .principal(usernamePasswordAuthentication)
                    .authorizationGrantType(new AuthorizationGrantType("password"))
                    .authorizedScopes(passwordGrant.getScopes())
                    .build();

            OAuth2Token generatedRefreshToken = tokenGenerator.generate(refreshTokenContext);
            if (generatedRefreshToken instanceof OAuth2RefreshToken) {
                refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
            }
        }

        Map<String, Object> additionalParameters = new HashMap<>();

        return new OAuth2AccessTokenAuthenticationToken(
                registeredClient,                    // RegisteredClient
                usernamePasswordAuthentication,      // Authentication (clientPrincipal)
                accessToken,                         // OAuth2AccessToken
                refreshToken,                        // OAuth2RefreshToken (nullable)
                additionalParameters                 // additionalParameters
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PasswordGrantAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
