package com.example.demo.security.jwt.repository;

import com.example.demo.security.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtTokenRepository extends JpaRepository<RefreshToken, Long> {
    public boolean existsByRefreshToken(String token);
}
