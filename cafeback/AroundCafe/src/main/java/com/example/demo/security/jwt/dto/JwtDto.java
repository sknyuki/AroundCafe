package com.example.demo.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class JwtDto {

    private final String type = "Bearer";
    private final String accessToken;
    private final Long accessTokenExp;
    private final String refreshToken;
    private final Long refreshTokenExp;
    private final String email;
    private final String role;

    @Builder
    public JwtDto(String accessToken, Long accessTokenExp, String refreshToken, Long refreshTokenExp, String email, String role) {
        this.accessToken = accessToken;
        this.accessTokenExp = accessTokenExp;
        this.refreshToken = refreshToken;
        this.refreshTokenExp = refreshTokenExp;
        this.email = email;
        this.role = role;
    }
}
