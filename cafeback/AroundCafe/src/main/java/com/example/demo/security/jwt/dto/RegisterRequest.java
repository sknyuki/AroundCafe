package com.example.demo.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Builder
@Getter
public class RegisterRequest {

    // Member Column
    private final String email;
    private final String username;
    private final String password;
    private final String phoneNum;
    private final String birth;

    // Cafe Column
    private final String cafeName;
    private final String cafeBisNo;

    // Role, SocialType
    private final String role;
    private final String socialType;
}
