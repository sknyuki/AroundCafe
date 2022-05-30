package com.example.demo.member.entity;

public enum MemberRoleType {


    ROLE_USER("USER"),
    //카페 RoleName을 뭘로할까요?
    ROLE_CAFE("CAFE"),
    ROLE_ADMIN("ADMIN");

    private final String value;
    private final String ROLE_PREFIX = "ROLE_";

    MemberRoleType(String value) {
        this.value = value;
    }

    public String getRoleType() {
        return ROLE_PREFIX + value.toUpperCase();
    }

    public boolean isEquals(String name){
        return this.toString().equals(name);
    }
}
