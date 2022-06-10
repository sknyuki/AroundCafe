package com.example.demo.security.jwt.controller;

import com.example.demo.common.exception.BadRequestException;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.security.jwt.dto.JwtDto;
import com.example.demo.security.jwt.dto.LoginRequest;
import com.example.demo.security.jwt.dto.OAuth2Request;
import com.example.demo.security.jwt.dto.RegisterRequest;
import com.example.demo.security.jwt.service.AuthServiceImpl;
import com.example.demo.security.jwt.service.JwtService;
import com.example.demo.common.dto.MessageResponse;
import com.example.demo.security.jwt.service.RedisServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.http.HttpResponse;

import static com.example.demo.security.jwt.filter.JwtFilter.MIN_TIME_TO_REFRESH;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {


    private final MemberRepository memberRepository;
    private final AuthServiceImpl authService;
    private final JwtService jwtService;
    private final RedisServiceImpl redisService;
    private final AuthenticationManager authenticationManager;

    //Token 발급이 되지 않은 경우
    @PostMapping("/login")
    public ResponseEntity<?> localLogin(@Valid @RequestBody LoginRequest loginRequest) {
        // loginRequest를 받아 authenticationManager를 통하여 authentication 인스턴스 생성
        // UsernamePasswordAuthenticationToken은 principal과 credential을 받아와 저장, authenctication false 저장
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        // securityContext에 authentication instance 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Role, memNo을 불러오기 위하여 Member Instance 생성 (member Id가 없을시 RuntimeException 반환)
        Member member = memberRepository.findByMemId(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Member Not Found with member Id : " + loginRequest.getEmail()));
        // accessToken, refreshToken 발급
        JwtDto jwtDto = authService.createToken(member);
        // jwtDto를 반환
        return ResponseEntity.ok(jwtDto);
    }


    //oauth 형태로 로그인 하는 경우
    @PostMapping("/oauth")
    public ResponseEntity<?> oauthLogin(@Valid @RequestBody OAuth2Request oauth2Request) {
        // SocialNo, MemId 존재여부 확인
        boolean isMemberExistsBySocialNo = memberRepository.existsBySocialNo(oauth2Request.getSocialNo());
        boolean isMemberExistsByMemId = memberRepository.existsByMemId(oauth2Request.getEmail());
        // SocialNo가 존재 하는 경우
        if(isMemberExistsBySocialNo){
            // member Instance 생성
            Member member = memberRepository.findBySocialNo(oauth2Request.getSocialNo())
                    .orElseThrow(() -> new UsernameNotFoundException("Member Not Found with social No: " + oauth2Request.getSocialNo()));

            // E-mail이 다르고 같은 SocialType이라면
            if(!oauth2Request.getEmail().equals(member.getMemId()) && member.getSocialType().getName().equals(oauth2Request.getSocialType())) {
                // Email을 불러와서 set --- Setter를 써야하나? 아니면 Builder로 하나하나 불러와서 저장해야하나?
                member.setMemId(oauth2Request.getEmail());
                memberRepository.save(member);
            }
            // JwtDto 생성
            JwtDto jwtDto = authService.createToken(member);
            // jwtDto 반환
            return ResponseEntity.ok(jwtDto);
        }
        // SocialNo, MemId가 존재하지 않는 경우
        else if(!isMemberExistsBySocialNo && !isMemberExistsByMemId){
            // registerRequest 생성
            RegisterRequest registerRequest = RegisterRequest.builder()
                    .socialType(oauth2Request.getSocialType())
                    .socialNo(oauth2Request.getSocialNo())
                    .email(oauth2Request.getEmail())
                    .imageUrl(oauth2Request.getImageUrl())
                    .phoneNum(oauth2Request.getPhoneNum())
                    .username(authService.generateNickname(10))
                    .password(authService.generateString(12))
                    .birth(oauth2Request.getBirthday())
                    .role("USER")
                    .build();
            // member 가입
            Member member = authService.createMember(registerRequest);
            // Token 생성
            JwtDto jwtDto = authService.createToken(member);

            return ResponseEntity.ok(jwtDto);
        }
        else {
            // 이미 Email은 존재하지만, 다른 SocialType일때 -- 우선 메시지만 전달
            String memId = oauth2Request.getEmail();
            Member member = memberRepository.findByMemId(memId)
                    .orElseThrow(()-> new UsernameNotFoundException("No User"));
            String socialType = member.getSocialType().getName();
            return ResponseEntity.ok().body("E-mail: " + memId + " is already signed by "  + socialType + ". please login by " + socialType);
        }
    }

    // AuthService로 기능 이동할지 고민중
    @PostMapping("/register")
    public ResponseEntity<?> localRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        // 이메일이 존재한다면, Exception 반환
        if (memberRepository.existsByMemId(registerRequest.getEmail())) {
            throw new BadRequestException("Error: Email address already in use!");
        }
        Member member = authService.createMember(registerRequest);

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
    public String logout(@RequestHeader(value = "refresh_Token") String refreshToken) {
        redisService.deleteByKey(refreshToken);
        HttpResponse<String> response;

        return "Logout";
    }


    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestHeader(value = "Authorization") String access, @RequestHeader(value = "refresh_Token") String refresh) {
        // accessToken, refreshToken 받아오기
        String accessToken = access.substring(7);
        String refreshToken = refresh.substring(7);
        
        // accessToken이 기간만료 제외 검증되지 않았거나, refreshToken이 검증되지 않았거나, Redis Server에 존재하지 않는다면
        // 401 에러 반환
        if(!jwtService.validateJwtTokenWithOutExpiration(accessToken)
                || !jwtService.validateJwtToken(refreshToken)
                || !redisService.isRefreshTokenExists(refreshToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // refreshToken을 통하여 Member Instance 생성
        Member member = memberRepository.findByMemNo(redisService.getValueByKey(refreshToken))
                .orElseThrow(() -> new UsernameNotFoundException("Member Not Found with member Id from RefreshToken : " + refreshToken));

        // accessToken 재발급 후 헤더 세팅
        String newAccessToken = jwtService.generateAccessToken(member);

        // refreshToken의 만료시간이 최소 재발급시간보다 낮다면
        if (jwtService.isTokenNeedReissue(refreshToken, MIN_TIME_TO_REFRESH)) {
            // refreshToken 삭제, 재발급 후 response 헤더 세팅
            String newRefreshToken = jwtService.generateRefreshToken();
            redisService.deleteByKey(refreshToken);
            redisService.setKeyAndValue(newRefreshToken, member.getMemNo());

            return ResponseEntity.ok(JwtDto.builder()
                    .accessToken(newAccessToken)
                    .accessTokenExp(jwtService.tokenExpTime(newAccessToken))
                    .refreshToken(newRefreshToken)
                    .refreshTokenExp(jwtService.tokenExpTime(newRefreshToken))
                    .build()
            );
        }
        else{
            return ResponseEntity.ok(JwtDto.builder()
                    .accessToken(newAccessToken)
                    .accessTokenExp(jwtService.tokenExpTime(newAccessToken))
                    .refreshToken(refreshToken)
                    .refreshTokenExp(jwtService.tokenExpTime(refreshToken))
                    .build()
            );
        }
    }
}
