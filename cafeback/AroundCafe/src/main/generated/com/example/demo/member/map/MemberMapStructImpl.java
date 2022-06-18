package com.example.demo.member.map;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.dto.MemberDto.MemberDtoBuilder;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.Member.MemberBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-17T18:07:17+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Azul Systems, Inc.)"
)
@Component
public class MemberMapStructImpl implements MemberMapStruct {

    @Override
    public Member toEntity(MemberDto dto) {
        if ( dto == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.memNo( dto.getMemNo() );
        member.memId( dto.getMemId() );
        member.memPw( dto.getMemPw() );
        member.memNick( dto.getMemNick() );
        member.phoneNum( dto.getPhoneNum() );
        member.socialType( dto.getSocialType() );
        member.role( dto.getRole() );

        return member.build();
    }

    @Override
    public MemberDto toDto(Member entity) {
        if ( entity == null ) {
            return null;
        }

        MemberDtoBuilder memberDto = MemberDto.builder();

        memberDto.memNo( entity.getMemNo() );
        memberDto.memId( entity.getMemId() );
        memberDto.memPw( entity.getMemPw() );
        memberDto.memNick( entity.getMemNick() );
        memberDto.phoneNum( entity.getPhoneNum() );
        memberDto.socialType( entity.getSocialType() );
        memberDto.role( entity.getRole() );

        return memberDto.build();
    }

    @Override
    public void updateFromDto(MemberDto dto, Member entity) {
        if ( dto == null ) {
            return;
        }
    }
}
