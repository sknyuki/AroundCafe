package com.example.demo.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class LoginRequest {

    private final String email;
    private final String password;
}
