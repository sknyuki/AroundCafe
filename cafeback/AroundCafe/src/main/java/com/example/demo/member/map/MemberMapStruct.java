package com.example.demo.member.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapStruct extends EntityMapStruct<MemberDto, Member> {
    MemberMapStruct instance = Mappers.getMapper(MemberMapStruct.class);
}
