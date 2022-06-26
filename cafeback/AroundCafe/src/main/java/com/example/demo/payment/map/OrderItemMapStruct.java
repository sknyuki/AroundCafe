package com.example.demo.payment.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.payment.dto.OrderItemRequest;
import com.example.demo.payment.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface OrderItemMapStruct extends EntityMapStruct<OrderItemRequest, OrderItem> {
    OrderItemMapStruct instance = Mappers.getMapper(OrderItemMapStruct.class);
}
