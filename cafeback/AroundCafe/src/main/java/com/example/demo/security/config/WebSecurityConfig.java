package com.example.demo.security.config;

import com.example.demo.security.jwt.exceptions.AccessDeniedJwt;
import com.example.demo.security.jwt.filter.JwtFilter;
import com.example.demo.security.jwt.exceptions.AuthEntryPointJwt;
import com.example.demo.security.service.CorsConfigurationSource;
import com.example.demo.security.service.MemberDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberDetailsServiceImpl memberDetailsService;
    private final CorsConfigurationSource corsConfigurationSource;
    private final AuthEntryPointJwt unAuthorizedHandler;
    private final AccessDeniedJwt accessDeniedHandler;
    private final JwtFilter jwtFilter;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //AuthenticationManagerBuilder를 통해서 UserDetatilsService, PasswordEncoder 설정
        authenticationManagerBuilder.userDetailsService(memberDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    // AuthenticationManager 객체 Bean 등록
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    // BCrypt 패스워드 인코더 객체 Bean 등록
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 기능 Disable
                .csrf().disable()
                // CORS 설정 (@CrossOrigin(인증 x), CORS 소스에 등록(인증 O))
                .cors().configurationSource(corsConfigurationSource.getSource())
                .and()
                //세션 Creation Stateless == 세션 상태 저장 하지않음.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // formLogin 비활성화(JWT 로그인 사용)
                .formLogin().disable()
                // 기본 로그인 화면 비활성화
                .httpBasic().disable()
                // 첫 인증시 발생하는 에러 AuthEntryPointJwt를 통하여 처리
                .exceptionHandling()
                    .authenticationEntryPoint(unAuthorizedHandler)
                    .accessDeniedHandler(accessDeniedHandler)
                .and()
                .authorizeRequests()
                    // Preflight Request 접근 가능
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                    // 권한 없이 접근 가능한 패턴
                    .antMatchers("/auth/**").permitAll()
                    // ADMIN 권한이 필요한 패턴
                    //.antMatchers("/admin/**").hasAnyRole("ADMIN")
                    // cafe, admin 권한이 필요한 패턴
                    //.antMatchers("/cafe/**").hasAnyRole("CAFE","ADMIN")
                    // 나머지 모든 Request는 Authenticated되어 있어야 함
                    //.anyRequest().authenticated()
                    .anyRequest().permitAll()
                .and()
                //헤더 정보의 x-frame-options 비활성화
                .headers().frameOptions().disable()
                .and()
                //UsernamePasswordAuthenticationFilter(Security Filter 내부의 Filter)보다 Token Filter 먼저 실행
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
