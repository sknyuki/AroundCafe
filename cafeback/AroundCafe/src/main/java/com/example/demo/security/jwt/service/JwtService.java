package com.example.demo.security.jwt.service;

import com.example.demo.member.entity.Member;
import com.example.demo.security.config.AppProperties;
import com.example.demo.security.dto.MemberPrincipal;
import com.example.demo.security.jwt.repository.JwtTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Service
public class JwtService {
    private final String jwtSecret;
    private final Long jwtExpirationMs;
    private final AppProperties appProperties;
    private JwtTokenRepository jwtTokenRepository;

    public JwtService(AppProperties appProperties) {
        this.appProperties = appProperties;
        this.jwtSecret = appProperties.getAuth().getJwtSecret();
        this.jwtExpirationMs = appProperties.getAuth().getJwtExpirationMs();
    }

    // JWT 엑세스 토큰 생성
    public String generateAccessToken(Authentication authentication) {
        // authentication을 통해 principal instance 생성
        Object principal = authentication.getPrincipal();
        // principal type_cast를 통하여 userPrincipal instance 생성
        MemberPrincipal memberPrincipal = (MemberPrincipal) principal;
        // userPrincipal instance에서 id값을 받아와 id instance 생성
        String id = String.valueOf(memberPrincipal.getId());
        // jwt 빌드 후 반환
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    public String generateAccessToken(Member member) {
        return Jwts.builder()
                .setSubject(member.getMemId())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // payload 제외하고 RefreshToken 생성함
    // 유저정보가 전혀 없이 만드는게 맞나???? --- 우선 PayLoad 안 담기로함!
    public String generateRefreshToken() {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs * 1000))
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Sign Key 가져오기
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰에서 UserId 가져오기
    public Long getMemberIdFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    // RefreshToken 존재 여부 확인
    public boolean isRefreshTokenExists(String refreshToKen) {
        return jwtTokenRepository.existsByRefreshToken(refreshToKen);
    }

    // JWT 토큰 검증하기
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)) // Set SignKey
                    .build()
                    .parseClaimsJws(authToken); // 파싱 및 검증, 실패시 에러
            return true;
        }
        //에러 헨들링
        catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
