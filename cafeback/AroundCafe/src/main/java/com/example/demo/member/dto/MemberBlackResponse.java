package com.example.demo.member.dto;

import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberBlackResponse {
    private Long memNo;
    private String memNick;
    private String phoneNum;
    private String memBirth;
    private SocialType socialType;
    private MemberRole role;
    private Boolean isMemberOnBlacklist;
}
