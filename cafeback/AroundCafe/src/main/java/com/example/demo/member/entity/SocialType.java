package com.example.demo.member.entity;

public enum SocialType {
    LOCAL("LOCAL"),
    KAKAO("KAKAO"),
    NAVER("NAVER");

    private final String name;

    SocialType(String name) { this.name = name; }
    public String getName() { return name; }
    public boolean isEquals(String authority) { return this.name.equals(authority); }
}