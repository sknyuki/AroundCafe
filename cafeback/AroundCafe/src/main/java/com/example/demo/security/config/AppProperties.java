package com.example.demo.security.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth;

    @Getter
    @RequiredArgsConstructor
    public static final class Auth {
        private final String jwtSecret;
        private final Long jwtExpirationMs;
    }

}