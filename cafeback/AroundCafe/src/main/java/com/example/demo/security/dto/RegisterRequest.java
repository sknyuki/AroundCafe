package com.example.demo.security.dto;

import lombok.Builder;
import lombok.Data;
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
    private final String imageUrl;

    // Social Column
    private final String socialType;
    private final String socialNo;

    // Role
    private final String role;

    // Cafe Column
    private final String cafeBisNo;
}
