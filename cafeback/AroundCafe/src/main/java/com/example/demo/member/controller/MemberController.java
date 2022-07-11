package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberBlackResponse;
import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.dto.MemberResponse;
import com.example.demo.member.entity.Member;
import com.example.demo.member.map.MemberMapStruct;
import com.example.demo.member.map.MemberResponseMapStruct;
import com.example.demo.member.service.MemberService;
import com.example.demo.payment.service.PaymentService;
import com.example.demo.security.annotation.CurrentMember;
import com.example.demo.security.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto postMember(@RequestBody MemberDto memberDto) {
        Member member = MemberMapStruct.instance.toEntity(memberDto);
        return MemberMapStruct.instance.toDto(memberService.save(member));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse getMember(@CurrentMember MemberPrincipal principal) {
        Member member = memberService.findByMemNo(principal.getId());
        MemberResponse memberResponse = MemberResponseMapStruct.instance.toDto(member);
        memberResponse.setRoleType(member.getRole().getName().getValue());
        return memberResponse;
    }

    @GetMapping("/{memNo}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse getMember(@PathVariable("memNo") Long memNo) {
        Member member = memberService.findByMemNo(memNo);
        MemberResponse memberResponse = MemberResponseMapStruct.instance.toDto(member);
        memberResponse.setRoleType(member.getRole().getName().getValue());
        return memberResponse;
    }

    @DeleteMapping("/{memNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable("memNo") Long memNo) {
        paymentService.setMemberNull(memNo);
        memberService.deleteByMemNo(memNo);
    }

    @PutMapping(value = "/modifyMember", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void modifyMember(@RequestPart(value = "info", required = false) MemberDto memberDto,
                             @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList) throws IOException {
        log.info("member" + memberDto.getMemNo());
        if(fileList != null){
            try{
                for(MultipartFile multipartFile : fileList) {
                    String filename = memberDto.getMemNo() + multipartFile.getOriginalFilename();
                    FileOutputStream writer = new FileOutputStream(
                            "../../cafefront/around_cafe/src/assets/images/memberImg/"  + filename);
                    log.info("save complete!"+ filename);
                    writer.write(multipartFile.getBytes());
                    writer.close();

                    memberService.addImgmodifyMember(memberDto, filename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else memberService.noImgmodifyMember(memberDto);
    }

    @PutMapping("/changePassword")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(@RequestBody MemberDto memberDto) {
        log.info("change pw");
        Member member = memberService.findByMemNo(memberDto.getMemNo());
        memberService.changeMemberPassword(member, memberDto.getMemPw());
    }
    @GetMapping("/admin/memberlist")
    @ResponseStatus(HttpStatus.OK)
        public List<MemberBlackResponse> list(){

        log.info("adminlist");
        return memberService.list();
    }

    //블랙 리스트 설정

    @PostMapping("/{memNo}")
    public boolean balckToTrue (@PathVariable("memNo") Long membNo) {
        log.info ("black check" );
        return memberService.balckToTrue(membNo);
    }

}

