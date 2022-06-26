package com.example.demo.security.jwt.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.MemberRoleType;
import com.example.demo.member.entity.SocialType;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.security.dto.JwtDto;
import com.example.demo.security.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final CafeRepository cafeRepository;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    public JwtDto createToken(Member member) {
        // accessToken, refreshToken 생성
        String accessToken = jwtService.generateAccessToken(member);
        String refreshToken = jwtService.generateRefreshToken();
        // 기존에 refreshKey가 있었다면 삭제
        redisService.deleteByKey(refreshToken);
        // redis에 리프레시 토큰 저장
        redisService.setKeyAndValue(refreshToken, member.getMemNo());
        // role to String
        String role = member.getRole().getName().getValue();
        Long cafeNo;
        // Role이 카페이면 cafeNo 반환
        if(Objects.equals(role, "CAFE")) {
            cafeNo = cafeRepository.findByMemberNo(member.getMemNo()).orElseThrow().getCafeNo();
        }
        else {
            cafeNo = null;
        }
        // 저장한 값을 JwtDto로 반환
        return JwtDto.builder()
                .memNo(member.getMemNo())
                .email(member.getMemId())
                .accessToken(accessToken)
                .accessTokenExp(jwtService.tokenExpTime(accessToken))
                .refreshToken(refreshToken)
                .refreshTokenExp(jwtService.tokenExpTime(refreshToken))
                .role(role)
                .nickname(member.getMemNick())
                .cafeNo(cafeNo)
                .build();
    }

    public Member createMember(RegisterRequest registerRequest) {
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
                .memImg(registerRequest.getImageUrl())
                .memBirth(registerRequest.getBirth())
                .socialType(SocialType.valueOf(registerRequest.getSocialType()))
                .socialNo(registerRequest.getSocialNo())
                .role(memberRole)
                .build();

        // userRepository를 통하여 생성한 user instance를 DB에 저장
        memberRepository.save(member);

        // registerRequest의 Role이 Cafe일때, Cafe Entity 생성후 저장
        if (registerRequest.getRole().equals("CAFE")) {
            Cafe cafe = Cafe.builder()
                    .cafe_name(registerRequest.getUsername())
                    .cafe_bis_no(registerRequest.getCafeBisNo())
                    .member(member)
                    .build();

            cafeRepository.save(cafe);
        }
        return member;
    }
    public String generateNickname(int length) {
        String nickname;
        do {
            nickname = generateString(length);
        } while (
                memberRepository.existsByMemNick(nickname)
        );
        return nickname;
    }

    public String generateString(int length) {
        return RandomString.make(length);
    }
}
