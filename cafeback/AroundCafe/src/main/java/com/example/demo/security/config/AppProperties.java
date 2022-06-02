package com.example.demo.security.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth;
    private final OAuth2 oauth2;

    @Getter
    @RequiredArgsConstructor
    public static final class Auth {
        private final String jwtSecret;
        private final Long jwtExpirationMs;
    }

    @Getter
    @RequiredArgsConstructor
    public static final class OAuth2 {
        private final List<String> authorizedRedirectUris;
    }
}