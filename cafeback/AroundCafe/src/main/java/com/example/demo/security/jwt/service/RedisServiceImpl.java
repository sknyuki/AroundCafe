package com.example.demo.security.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    // RedisTemplate<String, String> 일때 사용
    private final StringRedisTemplate stringRedisTemplate;

    // 키-벨류 설정
    public void setKeyAndValue(String token, String email) {
        ValueOperations<String, String> value = stringRedisTemplate.opsForValue();
        value.set(token, email, Duration.ofMinutes(5)); // 시간 조절 예정 -- 우선 5분
    }

    // 키값으로 벨류 get
    public String getValueByKey(String token) {
        ValueOperations<String, String> value = stringRedisTemplate.opsForValue();
        return value.get(token);
    }

    // 키값을 받아 키-벨류 삭제
    public void deleteByKey(String token) {
        stringRedisTemplate.delete(token);
    }

    public boolean isRefreshTokenExists(String token) {
        return getValueByKey(token) != null;
    }
}