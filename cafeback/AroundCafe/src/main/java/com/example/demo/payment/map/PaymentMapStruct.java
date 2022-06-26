package com.example.demo.payment.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.payment.dto.PaymentRequest;
import com.example.demo.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapStruct extends EntityMapStruct<PaymentRequest, Payment> {
    PaymentMapStruct instance = Mappers.getMapper(PaymentMapStruct.class);
}
