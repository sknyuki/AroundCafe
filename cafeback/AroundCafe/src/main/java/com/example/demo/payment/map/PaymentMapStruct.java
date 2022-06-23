package com.example.demo.payment.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.entity.Member;
import com.example.demo.payment.dto.PaymentDto;
import com.example.demo.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapStruct extends EntityMapStruct<PaymentDto, Payment> {
    PaymentMapStruct instance = Mappers.getMapper(PaymentMapStruct.class);
}
