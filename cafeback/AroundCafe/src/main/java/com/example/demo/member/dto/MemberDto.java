package com.example.demo.member.dto;

import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.SocialType;
import com.example.demo.payment.entity.Payment;
import com.example.demo.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MemberDto {
    private Long memNo;
    private String socialNo;
    private Integer memPoint;
    private String memId;
    private String memPw;
    private String memNick;
    private String memImg;
    private String phoneNum;
    private String memBirth;
    private SocialType socialType;
    private MemberRole role;
}
