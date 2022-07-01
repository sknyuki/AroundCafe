package com.example.demo.member.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.member.dto.MemberBlackResponse;
import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface MemberBlackResponseStruct extends EntityMapStruct<MemberBlackResponse, Member> {
    MemberBlackResponseStruct instance = Mappers.getMapper(MemberBlackResponseStruct.class);
}
