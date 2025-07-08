package com.uitgis.ciams.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAuth2ResourceServerConfig {

    @Value("${security.oauth2.client.resource-ids}")
    private String resourceIds;

    private static final String[] PERMIT_ALL = {
            "/rsa/key",
            "/error/**",
            "/cmm/signUp",
            "/api/v1/login/**",
            "/api/v1/config/**",
            "/api/v1/file/**",
            "/api/v1/plan/area/list",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 정책: STATELESS
                )
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화UitPasswordEncoder
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PERMIT_ALL).permitAll() // 허용된 엔드포인트
                        .requestMatchers("/api/**").authenticated() // `/api/**` 인증 필요
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()) // JWT 설정
                        )
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()) // Access Denied 처리기
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) // 인증 실패 처리기
                                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                );

        return http.build();
    }

    /**
     * JWT 토큰에서 권한 정보를 추출하는 설정
     */
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // 커스텀 권한 매핑이 필요한 경우 설정 추가 가능
        return converter;
    }

}
