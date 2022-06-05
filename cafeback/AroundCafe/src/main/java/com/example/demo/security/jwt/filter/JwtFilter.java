package com.example.demo.security.jwt.filter;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.security.jwt.service.JwtService;
import com.example.demo.security.jwt.service.RedisServiceImpl;
import com.example.demo.security.service.MemberDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final MemberDetailsServiceImpl memberDetailsService;
    private final RedisServiceImpl redisService;

    @Override
    // 필터 체인 안에 있는 필터 생성
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

      try{
          // request 정보(header)에서 JWT Access Token을 가져옴
          String accessToken = resolveAccessToken(request);
          String refreshToken = resolveRefreshToken(request);

          // accessToken값이 존재하며, JWT 검증결과 정상 토큰일 경우
          if(accessToken != null && jwtService.validateJwtToken(accessToken)) {
              setAuthentication(accessToken, request);
          }
          // accessToken, refreshToken이 not null이며, accessToken이 만료 되었을경우
          else if (accessToken != null && refreshToken != null && jwtService.validateJwtTokenWithOutExpiration(accessToken)) {
              // refreshToken 검증
              boolean validateRefreshToken = jwtService.validateJwtToken(refreshToken);
              // refreshToken 존재 확인
              boolean isRefreshToken = redisService.isRefreshTokenExists(refreshToken);

              // refreshToken이 검증되었고 존재하고 있다면,
              if(validateRefreshToken && isRefreshToken) {
                  // refreshToken을 통하여 Member Instance 생성
                  Member member = memberRepository.findByMemId(redisService.getValueByKey(refreshToken))
                          .orElseThrow(() -> new UsernameNotFoundException("Member Not Found with member Id from RefreshToken : " + refreshToken));
                  // accessToken, refreshToken 재발급
                  String newAccessToken = jwtService.generateAccessToken(member);
                  String newRefreshToken = jwtService.generateRefreshToken();
                  // redis에 리프레시 토큰 삭제후 저장
                  redisService.deleteByKey(refreshToken);
                  redisService.setKeyAndValue(newRefreshToken, member.getMemId());
                  // authentication 세팅
                  setAuthentication(newAccessToken, request);
              }
          }
      } catch(Exception e) {
          // 에러 로그 기록
          log.error("유저 Authentication을 세팅할 수 없습니다 : {}", e.getMessage());
      } finally {
          // 필터체인 doFilter 메소드 호출
          filterChain.doFilter(request, response);
      }
    }

    // authentication 설정
    private void setAuthentication(String token, HttpServletRequest request) {
        // JWT에서 userId 값을 가져옴
        Long memberId = jwtService.getMemberIdFromJwtToken(token);
        // userId 기준으로 principal 조회
        UserDetails userDetails = memberDetailsService.loadUserById(memberId);
        // principal 정보 기준으로 인증 토큰 객체 생성
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // 인증 토큰 객체 Details 필드에 WebAuthenticationDetailsSource 정보 세팅
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // SecurityContextHolder에 인증 토큰 정보 세팅
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    //request 정보의 헤더에서 JWT Access Token을 가져옴
    private String resolveAccessToken(HttpServletRequest request) {
        // 헤더에서 "Authorization 값을 가져옴
        String headerAuth = request.getHeader(AUTHORIZATION_HEADER);
        // Authorization Key가 있고, value 값이 Bearer로 시작하는 경우
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER_PREFIX)) {
            // "Bearer " 이후의 값을 반환
            return headerAuth.substring(7);
        }
        return null;
    }
    // RefreshToken에도 Bearer를 포함해야하는가?
    private String resolveRefreshToken(HttpServletRequest request) {
        // 헤더에서 Key가 RefreshToken인 값을 가져옴
        String refreshToken = request.getHeader("RefreshToken");
        // RefreshToken Key가 있다면
        if (StringUtils.hasText(refreshToken)) {
            // RefreshToken 반환
            return refreshToken;
        }
        // 없다면 null 반환
        return null;
    }
}
