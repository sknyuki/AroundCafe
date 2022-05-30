package com.example.demo.member.service;

import com.example.demo.member.entity.Member;

public interface MemberService {
    public Member findByMemNo(Long id);

    public Member save(Member user);

    public void deleteByMemNo(Long id);
}
