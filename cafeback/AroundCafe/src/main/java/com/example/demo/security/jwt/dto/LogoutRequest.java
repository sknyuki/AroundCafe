package com.example.demo.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LogoutRequest {

    private final String email;
}
