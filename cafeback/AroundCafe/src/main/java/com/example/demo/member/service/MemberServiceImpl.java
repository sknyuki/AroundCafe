package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    public Member findByMemNo(Long memNo) {
        return memberRepository.findById(memNo)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "Mem_No", memNo));
    }

    //회원 가입, 수정 등
    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    //회원 탈퇴
    @Override
    public void deleteByMemNo(Long memNo) {
        memberRepository.deleteById(memNo);
    }
}
