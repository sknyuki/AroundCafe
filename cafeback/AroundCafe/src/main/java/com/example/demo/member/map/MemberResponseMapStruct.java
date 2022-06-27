package com.example.demo.member.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.member.dto.MemberResponse;
import com.example.demo.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberResponseMapStruct extends EntityMapStruct<MemberResponse, Member> {
    MemberResponseMapStruct instance = Mappers.getMapper(MemberResponseMapStruct.class);
}
