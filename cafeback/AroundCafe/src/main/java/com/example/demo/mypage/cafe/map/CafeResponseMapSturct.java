package com.example.demo.mypage.cafe.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.mypage.cafe.dto.CafeStarAverResponse;
import com.example.demo.mypage.cafe.entity.Cafe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CafeResponseMapSturct extends EntityMapStruct<CafeStarAverResponse, Cafe> {
    CafeResponseMapSturct instance = Mappers.getMapper(CafeResponseMapSturct.class);
}
