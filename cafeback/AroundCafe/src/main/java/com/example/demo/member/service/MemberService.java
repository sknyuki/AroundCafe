package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRoleType;

import java.util.List;

public interface MemberService {
    public Member findByMemNo(Long memNo);

    public Member save(Member user);

    public void deleteByMemNo(Long memNo);
    public Boolean existsByMemId(String memId);
    public Boolean existsByMemNick(String memNick);
    public Member findByMemId(String memId);
    public Member findByMemNick(String memNick);
    public List<Member> findMembersByRoleType(MemberRoleType name);
    public void changeMemberPassword(Member member, String password);
}
