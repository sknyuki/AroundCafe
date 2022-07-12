package com.example.demo.member.service;

import com.example.demo.member.dto.MemberBlackResponse;
import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.MemberRoleType;
import com.example.demo.member.map.MemberBlackResponseStruct;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.common.exception.ResourceNotFoundException;
import com.example.demo.member.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Component
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    @Transactional
    public Member findByMemNick(String memNick) throws ResourceNotFoundException {
        return memberRepository.findByMemNick(memNick)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "Mem_Nick", memNick));
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
    public Boolean existsByMemId(String memId) {
        return memberRepository.existsByMemId(memId);
    }

    @Override
    public Boolean existsByMemNick(String memNick) {
        return memberRepository.existsByMemNick(memNick);
    }

    @Override
    public List<Member> findMembersByRoleType(MemberRoleType name) {
        List<MemberRole> memberRoles = memberRoleRepository.findAllByName(name);
        List<Member> members = new ArrayList<>();
        for (MemberRole memberRole : memberRoles) {
            Member member = memberRole.getMember();
            members.add(member);
        }
        return members;
    }

    @Override
    public void changeMemberPassword(Member member, String password) {
        member.setMemPw(passwordEncoder.encode(password));
        this.save(member);
    }

    @Override
    public void addImgmodifyMember(MemberDto memberDto, String filename) throws IOException {
        Member member = memberRepository.findByMemNo(memberDto.getMemNo()).orElseGet(null);

        //수정한 디테일에 이미지가 있고 + 기존에 저장한 이미지가 있다면, 기존 이미지 삭제 후 수정 이미지를 저장한다.
        //추가, 카톡 이미지는 http형식이기 때문에 삭제하지 않고 덮어씌우기 한다.
        if (member.getMemImg() != null && member.getMemImg().indexOf("k.kakaocdn.net") < 5) {
            Path filePath = Paths.get("../../cafefront/around_cafe/src/assets/images/memberImg/" + member.getMemImg());
            Files.delete(filePath);
            log.info("file delete complete");
        }

        member.setMemId(memberDto.getMemId());
        member.setMemNick(memberDto.getMemNick());
        member.setPhoneNum(memberDto.getPhoneNum());
        member.setMemBirth(memberDto.getMemBirth());
        member.setMemImg(filename);
        memberRepository.save(member);

    }

    @Override
    public void noImgmodifyMember(MemberDto memberDto) throws IOException {
        Member member = memberRepository.findByMemNo(memberDto.getMemNo()).orElseGet(null);
        //수정한 디테일에 이미지가 없다면 기존 이미지를 가져다 저장한다.

        member.setMemId(memberDto.getMemId());
        member.setMemNick(memberDto.getMemNick());
        member.setPhoneNum(memberDto.getPhoneNum());
        member.setMemBirth(memberDto.getMemBirth());
        memberRepository.save(member);
    }

    //멤버+블랙 조회 태호씨에게 질문
    @Transactional
    @Override
    public List<MemberBlackResponse> list() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberBlackResponse> memberBlackResponses = new ArrayList<>();

        for (Member member : memberList) {
            MemberBlackResponse memberBlackResponse = MemberBlackResponseStruct.instance.toDto(member);
            memberBlackResponse.setIsMemberOnBlacklist(member.getRole().getIsMemberOnBlacklist());
            memberBlackResponse.setName(member.getRole().getName().getValue());
            memberBlackResponses.add(memberBlackResponse);

        }

        return memberBlackResponses;
    }

    @Override
    public String blackChange(Long membNo) {
        MemberRole memberRole = memberRoleRepository.findById(membNo).orElseGet(null);
        if (!memberRole.getIsMemberOnBlacklist()) {
            log.info("멤버롤 블랙 확인"+memberRole.getIsMemberOnBlacklist());
            memberRole.setIsMemberOnBlacklist(true);
            memberRoleRepository.save(memberRole);
            return "블랙리스트로 등록하였습니다.";
        } else {
            memberRole.setIsMemberOnBlacklist(false);
            log.info("멤버롤 블랙 확인"+memberRole.getIsMemberOnBlacklist());
            memberRoleRepository.save(memberRole);
            return "블랙리스트를 해체하였습니다.";
        }
    }





//    @Override
//    public boolean balckToTrue (Long membNo){
//        MemberRole memberRole=findByMemId()

    @Scheduled(cron = "0 0 1 * * ?")
    public void giveBirthdayPoint(){
        LocalDate now = LocalDate.now();
        String nowDay = String.valueOf(now).substring(4);
       List<Member> members = memberRepository.findByMemBirthLike(nowDay);
       if(members.size() > 0) {
           for(int i = 0 ; i < members.size(); i++) {
               members.get(i).setMemPoint(members.get(i).getMemPoint() + 500);
           }
       }

    }
}
