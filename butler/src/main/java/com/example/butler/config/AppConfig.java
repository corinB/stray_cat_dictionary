package com.example.butler.config;

import com.example.butler.util.ImgUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ImgUtil imgUtil() {
        return new ImgUtil();
    }
}
