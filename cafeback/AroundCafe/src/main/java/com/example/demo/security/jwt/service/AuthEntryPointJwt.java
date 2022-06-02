package com.example.demo.security.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    // 비로그인 상태에서 인증 실패 시 AuthenticationException가 발생한 것을 포착하여 호출되는 메서드
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException ex) throws IOException {
        // 로그 출력
        log.error("Unauthorized error: {}", ex.getMessage());
        // 응답으로 상태 코드 401 값과 에러 메시지 "Error: Unauthorized"을 보냄
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), "Error: Unauthorized");
        //final String responseJson = objectMapper.writeValueAsString(apiError);
        //response.getWriter().print(responseJson);
    }
}
