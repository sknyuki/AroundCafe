package com.example.demo.security.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuth2Request {
    private String socialType;
    private String socialNo;
    private String email; // memId
    private String birthday; // memBirth
    private String phoneNum;
    private String imageUrl;
}
