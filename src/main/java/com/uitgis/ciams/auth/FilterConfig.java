package com.uitgis.ciams.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomAuthenticationFilter> customFilterRegistration(CustomAuthenticationFilter customFilter) {
        FilterRegistrationBean<CustomAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(customFilter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }
}
