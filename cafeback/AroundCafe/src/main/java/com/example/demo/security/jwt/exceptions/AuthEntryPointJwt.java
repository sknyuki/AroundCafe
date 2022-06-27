package com.example.demo.security.jwt.exceptions;

import com.example.demo.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private final JwtService jwtService;
    // 인증 실패 시 AuthenticationException가 발생한 것을 포착하여 호출되는 메서드
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException ex) throws IOException, ServletException {
        String accessToken;
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            // "Bearer " 이후의 값을 반환
            accessToken = headerAuth.substring(7);
        } else {
            accessToken = null;
        }
        if(accessToken != null && jwtService.validateJwtTokenWithOutExpiration(accessToken)){
            log.error("Unauthorized error: {}", "TokenExpired");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("token expired");
            response.getWriter().flush();
        }
        else {
            // 응답으로 상태 코드 401 값과 에러 메시지 "Error: Unauthorized"을 보냄
            log.error("Unauthorized error: {}", ex.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }



    }
}
