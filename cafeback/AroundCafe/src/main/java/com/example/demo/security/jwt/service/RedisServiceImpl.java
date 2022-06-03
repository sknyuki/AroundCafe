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
    public void setKeyAndValue(String email, String token) {
        ValueOperations<String, String> values = stringRedisTemplate.opsForValue();
        values.set(email, token, Duration.ofDays(1)); // 시간 조절 예정 -- 우선 하루
    }

    // 키값으로 벨류 get
    public String getValuesByKey(String email) {
        ValueOperations<String, String> values = stringRedisTemplate.opsForValue();
        return values.get(email);
    }

    // 키값을 받아 키-벨류 삭제
    public void deleteByKey(String email) {
        stringRedisTemplate.delete(email);
    }
}