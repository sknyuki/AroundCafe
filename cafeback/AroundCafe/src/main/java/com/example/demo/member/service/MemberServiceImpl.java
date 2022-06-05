package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Override
    @Transactional
    public Member findByMemNo(Long memNo) throws ResourceNotFoundException {
        return memberRepository.findByMemNo(memNo)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "Mem_No", String.valueOf(memNo)));
    }

    @Override
    @Transactional
    public Member findByMemId(String memId) throws UsernameNotFoundException {
        return memberRepository.findByMemId(memId)
                .orElseThrow(() -> new UsernameNotFoundException("Member Not Found with member Id : " + memId));
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

    @Override
    public Boolean existsByMemId(String memId){
        return memberRepository.existsByMemId(memId);
    }
}
