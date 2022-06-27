package com.example.demo.member.dto;

import com.example.demo.member.entity.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberResponse {
    private Long memNo;
    private Integer memPoint;
    private String memId;
    private String memNick;
    private String memImg;
    private String phoneNum;
    private String memBirth;
    private SocialType socialType;
    private String roleType;
}
