package com.example.demo.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class JwtDto {

    private final String type = "Bearer";
    private final String accessToken;
    private final Integer accessTokenExp;
    private final String refreshToken;
    private final Integer refreshTokenExp;
    private final String email;
    private final String nickname;
    private final String role;

    @Builder
    public JwtDto(String accessToken, Integer accessTokenExp, String refreshToken, Integer refreshTokenExp, String email, String nickname, String role) {
        this.accessToken = accessToken;
        this.accessTokenExp = accessTokenExp;
        this.refreshToken = refreshToken;
        this.refreshTokenExp = refreshTokenExp;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }
}
