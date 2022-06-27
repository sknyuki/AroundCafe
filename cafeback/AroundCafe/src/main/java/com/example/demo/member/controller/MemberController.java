package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.dto.MemberResponse;
import com.example.demo.member.entity.Member;
import com.example.demo.member.map.MemberMapStruct;
import com.example.demo.member.map.MemberResponseMapStruct;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.annotation.CurrentMember;
import com.example.demo.security.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto postMember(@RequestBody MemberDto memberDto) {
        Member member = MemberMapStruct.instance.toEntity(memberDto);
        return MemberMapStruct.instance.toDto(memberService.save(member));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse getMember(@CurrentMember MemberPrincipal principal) {
        Member member = memberService.findByMemNo(principal.getId());
        MemberResponse memberResponse = MemberResponseMapStruct.instance.toDto(member);
        memberResponse.setRoleType(member.getRole().getName().getValue());
        return memberResponse;
    }

    @DeleteMapping("/{memNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable("memNo") Long memNo) {
        memberService.deleteByMemNo(memNo);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void modifyMember(MemberDto memberDto) {
        memberService.modifyMember(memberDto);
    }
}

