package com.uitgis.ciams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = {"com.uitgis.auth", "com.uitgis.ciams"})
public class CiamsApiServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CiamsApiServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
}
