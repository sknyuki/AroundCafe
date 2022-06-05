package com.example.demo.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class JwtDto {

    private final String type = "Bearer";
    private final String accessToken;
    private final String refreshToken;
    private final String email;
    private final String role;

    @Builder
    public JwtDto(String accessToken, String refreshToken, String email, String role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = email;
        this.role = role;
    }
}
