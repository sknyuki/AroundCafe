package com.example.demo.payment.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.payment.dto.PaymentResponse;
import com.example.demo.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentResponseMapStruct extends EntityMapStruct<PaymentResponse, Payment> {
    PaymentResponseMapStruct instance = Mappers.getMapper(PaymentResponseMapStruct.class);
}
