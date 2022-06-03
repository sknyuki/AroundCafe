package com.example.demo.security.jwt.controller;

import com.example.demo.common.exception.BadRequestException;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.MemberRoleType;
import com.example.demo.member.entity.SocialType;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.security.jwt.dto.JwtDto;
import com.example.demo.security.jwt.dto.LoginRequest;
import com.example.demo.security.jwt.dto.LogoutRequest;
import com.example.demo.security.jwt.dto.RegisterRequest;
import com.example.demo.security.jwt.service.JwtService;
import com.example.demo.common.dto.MessageResponse;
import com.example.demo.security.jwt.service.RedisServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RedisServiceImpl redisService;

    private final CafeRepository cafeRepository;

    //Token 발급이 되지 않은 경우
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // loginRequest를 받아 authenticationManager를 통하여 authentication 인스턴스 생성
        // UsernamePasswordAuthenticationToken은 principal과 credential을 받아와 저장, authenctication false 저장
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // securityContext에 authentication instance 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // accessToken, refreshToken 생성
        String accessToken = jwtService.generateAccessToken(authentication);
        String refreshToken = jwtService.generateRefreshToken();

        // 기존에 refreshKey가 있었다면 삭제
        redisService.deleteByKey(loginRequest.getEmail());
        // redis에 리프레시 토큰 저장
        redisService.setKeyAndValue(loginRequest.getEmail(), refreshToken);

        // principal, accessToken, refreshToken을 반환하여 response
        return ResponseEntity.ok(JwtDto.builder()
                .email(loginRequest.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build()
        );
    }

    // AuthService로 기능 이동할지 고민중
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        // 이메일이 존재한다면, Exception 반환
        if(memberRepository.existsByMemId(registerRequest.getEmail())) {
            throw new BadRequestException("Error: Email address already in use!");
        }
        // MemberRole instance 생성
        MemberRole memberRole = MemberRole.builder()
                .isMemberOnBlacklist(false)
                .name(MemberRoleType.valueOf("ROLE_" + registerRequest.getRole()))
                .build();

        // 이메일이 존재하지 않는다면, signUpRequest를 통하여 user Instance 생성
        Member member = Member.builder()
                .memId(registerRequest.getEmail())
                .memNick(registerRequest.getUsername())
                .memPw(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNum(registerRequest.getPhoneNum())
                .memBirth(registerRequest.getBirth())
                .socialType(SocialType.valueOf(registerRequest.getSocialType()))
                .role(memberRole)
                .build();

        // userRepository를 통하여 생성한 user instance를 DB에 저장
        memberRepository.save(member);

        // registerRequest의 Role이 Cafe일때, Cafe Entity 생성후 저장
        if(registerRequest.getRole().equals("CAFE")) {
            Cafe cafe = Cafe.builder()
                    .cafe_name(registerRequest.getCafeName())
                    .cafe_bis_no(registerRequest.getCafeBisNo())
                    .member(member)
                    .build();
            
            cafeRepository.save(cafe);
        }

        // uri 헤더 반환
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user/me")
                .buildAndExpand(member.getMemNo())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        redisService.deleteByKey(logoutRequest.getEmail());
        return ResponseEntity.ok().body("로그아웃");
    }
}
