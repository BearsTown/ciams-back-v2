package com.uitgis.ciams.config;

import com.uitgis.ciams.auth.CustomAuthenticationProvider;
import com.uitgis.ciams.config.UitPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity//시큐리티 필터가 등록
public class WebSecurityConfig {


//    private final UserDetailsService userDetailService;

    /**
     * AuthenticationManager 설정 후 등록 (외부에서 사용)
     * PasswordEncoder를 사용하는 AuthenticationProvider 지정 (PasswordEncoder는 위에서 등록한 PasswordEncoder 사용)
     * 사용자 인증에 사용
     */
    @Bean
    public AuthenticationManager authenticationManager(CustomAuthenticationProvider customAuthenticationProvider) throws Exception {
        return new ProviderManager(customAuthenticationProvider);
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailService);
//        provider.setPasswordEncoder(uitPasswordEncoder());
//        return provider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public PasswordEncoder uitPasswordEncoder() {
        return new UitPasswordEncoder();
    }

}

