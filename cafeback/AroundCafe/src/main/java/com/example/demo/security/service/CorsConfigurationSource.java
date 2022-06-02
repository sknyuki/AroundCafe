package com.example.demo.security.service;


import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Component
public class CorsConfigurationSource {
    private final CorsConfiguration configuration;
    private final UrlBasedCorsConfigurationSource source;


    public CorsConfigurationSource() {
        this.configuration = new CorsConfiguration();
        this.source = new UrlBasedCorsConfigurationSource();
    }

    //CORS 설정 세팅 후 Source 리턴
    public UrlBasedCorsConfigurationSource getSource() {

        //자격 증명과 함께 요청을 할 수 있는지 여부 -- 해당 서버에서 Authorizeation으로 사용자 인증도 서비스 해야 함으로 True
        configuration.setAllowCredentials(false);
        // CORS요청을 허용할 사이트 -- 모든 주소에 대한 응답 허용
        configuration.addAllowedOrigin("http://localhost:8080");
        //특정 헤더를 가진 경우에만 CORS 요청을 허용 -- 모든 header에 대해 응답 허용
        configuration.addAllowedHeader("*");
        //CORS요청을 허용할 Http Method -- post, get, put, delete, patch등의 모든 요청을 허용
        configuration.addAllowedMethod("*");
        //클라이언트가 preflight 요청에 대한 응답을 캐시할 수 있는 시간(초) -- 5분
        configuration.setMaxAge(3000L);
        //모든 페이지에서 위와 같은 설정을 따름
        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
