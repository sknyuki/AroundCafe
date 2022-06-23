package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.map.MemberMapStruct;
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
    public ResponseEntity<?> postUser(@RequestBody MemberDto memberDto) {
        Member member = MemberMapStruct.instance.toEntity(memberDto);
        MemberDto responseMemberDto = MemberMapStruct.instance.toDto(memberService.save(member));
        return new ResponseEntity<>(responseMemberDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getUser(@CurrentMember MemberPrincipal principal) {
        Member member = memberService.findByMemNo(principal.getId());
        MemberDto memberDto = MemberMapStruct.instance.toDto(member);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        memberService.deleteByMemNo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

