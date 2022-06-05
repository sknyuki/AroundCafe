package com.example.demo.security.jwt.service;

public interface RedisService {

    public void setKeyAndValue(String email, String token);
    public String getValueByKey(String email);
    public void deleteByKey(String email);
}