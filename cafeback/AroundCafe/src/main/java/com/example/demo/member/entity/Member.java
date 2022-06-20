package com.example.demo.member.entity;

import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.common.entity.BaseDateTime;
import com.example.demo.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "Members")
public class Member extends BaseDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNo;

    @Column(unique = true)
    private String socialNo;

    @Column(unique = true)
    private String memId;

    private String memPw;

    @Column(unique = true)
    private String memNick;

    //이미지 경로 저장
    private String memImg;

    // Integer 형태로 1012345678 저장 or 010-1234-5678형태로 저장?
    // 우선 010-1234-5678형태로 입력한다고 가정
    @Column(unique = true)
    private String phoneNum;

    private String memBirth;

    private SocialType socialType;
    // ManyToMany 형태로 연결할지, OneToOne형태로 연결할지 고민중
    // ManyToMany의 경우 Role 컬럼의 isMemberOnBlacklist를 Member 컬럼으로 옮겨야함.
    // Set<E>형태로 여러가지 권한을 보유할 수 있게할 수 있음. 우리 서비스에서는 필요없을듯?
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private MemberRole role;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "memberInfo", orphanRemoval = true)
    private Cafe cafe;

    @OneToMany(mappedBy = "memberInfo",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Review> review = new ArrayList<>();

    @Builder
    public Member(Long memNo, String memId, String socialNo, String memPw, String memNick, String memImg, String phoneNum, String memBirth, SocialType socialType, MemberRole role) {
        this.memNo = memNo;
        this.socialNo = socialNo;
        this.memId = memId;
        this.memPw = memPw;
        this.memNick = memNick;
        this.memImg = memImg;
        this.phoneNum = phoneNum;
        this.memBirth = memBirth;
        this.socialType = socialType;
        this.role = role;
    }

}
