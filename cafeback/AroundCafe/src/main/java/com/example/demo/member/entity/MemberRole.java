package com.example.demo.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "member_role")
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberRoleType name;

    @Column(name = "blacklist")
    private Boolean isMemberOnBlacklist;

    @OneToOne(mappedBy = "role")
    private Member member;

    @Builder
    public MemberRole(Long id, MemberRoleType name, Boolean isMemberOnBlacklist, Member member){
        this.id = id;
        this.name = name;
        this.isMemberOnBlacklist = isMemberOnBlacklist;
        this.member = member;
    }

}
