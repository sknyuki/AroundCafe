package com.example.demo.security.service;

import com.example.demo.common.exception.ResourceNotFoundException;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.security.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {

        //memId(Email)로 User Instance 생성
        Member member = memberRepository.findByMemId(memId)
                .orElseThrow(() -> new UsernameNotFoundException("Member Not Found with member Id : " + memId));

        //User Instance 기준으로 UserPrincipal 객채 생성 후 리턴
        return MemberPrincipal.create(member);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {

        //id로 User Instance 생성
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

        //User Instance 기준으로 UserPrincipal 객체 생성 후 리턴
        return MemberPrincipal.create(member);
    }
}
