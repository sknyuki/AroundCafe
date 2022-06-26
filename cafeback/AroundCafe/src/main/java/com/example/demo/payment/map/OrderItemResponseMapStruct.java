package com.example.demo.payment.map;

import com.example.demo.common.map.EntityMapStruct;
import com.example.demo.payment.dto.OrderItemResponse;
import com.example.demo.payment.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemResponseMapStruct extends EntityMapStruct<OrderItemResponse, OrderItem> {
    OrderItemResponseMapStruct instance = Mappers.getMapper(OrderItemResponseMapStruct.class);
}
