package com.example.demo.security.jwt.filter;

import com.example.demo.security.jwt.service.JwtService;
import com.example.demo.security.service.MemberDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
// OncePerRequestFilter란?
// 특정 필터가 request당 한 번만 호출되도록 하는 것을 목적으로 하며, 요청이 필터 체인을 통과할 때 일부 인증 작업이 요청에 대해 한 번만 발생시키기 위해 상속 받아 사용하는 필터.
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberDetailsServiceImpl memberDetailsService;

    @Override
    //필터 체인 안에 있는 필터 생성
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

      try{
          // request 정보(header)에서 JWT Access Token을 가져옴
          String jwt = parseJwt(request);

          // JWT값이 존재하며, JWT 검증결과 정상 토큰일 경우
          if(jwt != null && jwtService.validateJwtToken(jwt)) {
              // JWT에서 userId 값을 가져옴
              Long userId = jwtService.getUserIdFromJwtToken(jwt);
              // userId 기준으로 principal 조회
              UserDetails userDetails = memberDetailsService.loadUserById(userId);
              // principal 정보 기준으로 인증 토큰 객체 생성
              UsernamePasswordAuthenticationToken authentication =
                      new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              // 인증 토큰 객체 Details 필드에 WebAuthenticationDetailsSource 정보 세팅
              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              // SecurityContextHolder에 인증 토큰 정보 세팅
              SecurityContextHolder.getContext().setAuthentication(authentication);
          }
      } catch(Exception e) {
          // 에러 로그 기록
          log.error("유저 Authentication을 세팅할 수 없습니다 : {}", e.getMessage());
      } finally {
          // 필터체인 doFilter 메소드 호출
          filterChain.doFilter(request, response);
      };
    }

    //request 정보의 헤더에서 JWT Access Token을 가져옴
    private String parseJwt(HttpServletRequest request) {
        // 헤더에서 "Authorization 값을 가져옴
        String headerAuth = request.getHeader("Authorization");
        // Authorization Key가 있고, value 값이 Bearer로 시작하는 경우
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            // "Bearer " 이후의 값을 반환
            return headerAuth.substring(7);
        }
        return null;
    }
}
