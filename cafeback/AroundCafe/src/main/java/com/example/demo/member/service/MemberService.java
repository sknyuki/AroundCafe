package com.example.demo.member.service;

import com.example.demo.member.entity.Member;

public interface MemberService {
    public Member findByMemNo(Long memNo);

    public Member save(Member user);

    public void deleteByMemNo(Long memNo);
    public Boolean existsByMemId(String memId);
    public Member findByMemId(String memId);
}
