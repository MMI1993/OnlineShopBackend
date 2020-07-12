package com.onlineshop.be.configure;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class WebConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET")
                .allowedOrigins("http://localhost:4200");
    }

}
