package com.erik.movie_find.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica CORS a todos os endpoints
                .allowedOrigins("*") // origem do seu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}