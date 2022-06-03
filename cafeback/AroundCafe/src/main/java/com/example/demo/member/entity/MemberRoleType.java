package com.example.demo.member.entity;

public enum MemberRoleType {


    ROLE_USER("USER"),
    ROLE_CAFE("CAFE"),
    ROLE_ADMIN("ADMIN");

    private final String ROLE_PREFIX = "ROLE_";
    private final String value;


    MemberRoleType(String value) {
        this.value = value;
    }

    // toUpperCase == 대문자로 변환
    public String getRoleType() {
        return ROLE_PREFIX + value.toUpperCase();
    }

    public String getValue() { return value; }

    public boolean isEquals(String name){
        return this.toString().equals(name);
    }
}
