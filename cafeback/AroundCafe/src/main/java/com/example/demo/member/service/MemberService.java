package com.example.demo.member.service;

import com.example.demo.member.dto.MemberBlackResponse;
import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.io.IOException;
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
    public void addImgmodifyMember(MemberDto memberDto,String filename) throws IOException;
    public void noImgmodifyMember(MemberDto memberDto) throws IOException;
    public List<Member> findMembersByRoleType(MemberRoleType name);
    public void changeMemberPassword(Member member, String password);
    public List<MemberBlackResponse> list();
    public boolean balckToTrue (Long membNo);
}
