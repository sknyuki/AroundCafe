package com.example.demo.member.dto;

import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.SocialType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {

    private Long memNo;
    private String memId;
    private String memPw;
    private String memNick;
    private String phoneNum;
    private SocialType socialType;
    private MemberRole role;
}
