package com.example.demo.security.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;


/*
 * 커스텀 어노테이션
 * Documented : javadoc으로 문서 생성 시 현재 어노테이션 설명 추가 <br/>
 * Retention : 어노테이션을 유지하는 정책 설정<br/>
 * Target : 어노테이션 적용 위치\
 * AuthenticationPrincipal 인증이후 편의적으로 현재 인증된 세션유저를 가져오기 위해 UserDetails 인터페이스를 구현한 유저 객체를 주입할 때 사용.
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentMember {

}