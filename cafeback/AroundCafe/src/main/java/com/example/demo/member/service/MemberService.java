package com.example.demo.member.service;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface MemberService {
    public Member findByMemNo(Long memNo);

    public Member save(Member user);

    public void deleteByMemNo(Long memNo);
    public Boolean existsByMemId(String memId);
    public Boolean existsByMemNick(String memNick);
    public Member findByMemId(String memId);
    public Member findByMemNick(String memNick);
    public void modifyMember(MemberDto memberDto);
    public List<Member> findMembersByRoleType(MemberRoleType name);
    public void changeMemberPassword(Member member, String password);
}
