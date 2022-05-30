package com.example.demo.member.entity;

import common.entity.BaseDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "Members")
public class Member extends BaseDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNo;

    @Column(unique = true)
    private String memId;

    private String memPw;

    @Column(unique = true)
    private String memNick;

    //이미지 경로 저장
    private String memImg;

    // Integer 형태로 1012345678 저장 or 010-1234-5678형태로 저장?
    // 우선 010-1234-5678형태로 입력한다고 가정
    // 휴대폰으로 찾기 없어지면, 컬럼삭제
    @Column(unique = true)
    private String phoneNum;

    // ManyToMany 형태로 연결할지, OneToOne형태로 연결할지 고민중
    // ManyToMany의 경우 Role 컬럼의 isMemberOnBlacklist를 Member 컬럼으로 옮겨야함.
    // Set<E>형태로 여러가지 권한을 보유할 수 있게할 수 있음. 우리 서비스에서는 필요없을듯?
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private MemberRole role;


    @Builder
    public Member(String memId, String memPw, String memNick, String memImg, String phoneNum, MemberRole role) {
        this.memId = memId;
        this.memPw = memPw;
        this.memNick = memNick;
        this.memImg = memImg;
        this.phoneNum = phoneNum;
        this.role = role;
    }

}