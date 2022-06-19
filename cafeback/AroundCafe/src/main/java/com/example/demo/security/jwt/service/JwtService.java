package com.example.demo.security.jwt.service;

import com.example.demo.member.entity.Member;
import com.example.demo.security.config.AppProperties;
import com.example.demo.security.dto.MemberPrincipal;
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
    private final Long jwtExpirationMs; // 1분
    private final AppProperties appProperties;

    public JwtService(AppProperties appProperties) {
        this.appProperties = appProperties;
        this.jwtSecret = appProperties.getAuth().getJwtSecret();
        this.jwtExpirationMs = appProperties.getAuth().getJwtExpirationMs();
    }

    // JWT 엑세스 토큰 생성
    public String generateAccessToken(Member member) {
        String id = String.valueOf(member.getMemNo());
        return Jwts.builder()
                .setId(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // payload 제외하고 RefreshToken 생성
    public String generateRefreshToken() {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs * 3))
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Sign Key 가져오기
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰에서 UserId(memNo) 가져오기(만료되지 않은경우)
    public Long getMemberIdFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getId());
    }

    // JWT 토큰 검증하기
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)) // Set SignKey
                    .build()
                    .parseClaimsJws(token); // 파싱 및 검증, 실패시 에러
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

    // Expiration Exception을 제외한 나머지는 검증이 되었을 경우
    public boolean validateJwtTokenWithOutExpiration(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)) // Set SignKey
                    .build()
                    .parseClaimsJws(token); // 파싱 및 검증, 실패시 에러
            return true;
        }
        catch(ExpiredJwtException e) {
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    //토큰 만료 시간
    public Long tokenExpDate(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token);
        return claims.getBody().getExpiration().getTime();
    }

    public Long tokenExpTime(String token) {
        return tokenExpDate(token) - System.currentTimeMillis();
    }

    // 리프 레시 토큰 만료 시간 검증
    public boolean isTokenNeedReissue(String token, long minTimeToRefresh) {
        return tokenExpDate(token) < System.currentTimeMillis() + minTimeToRefresh;
    }


}
