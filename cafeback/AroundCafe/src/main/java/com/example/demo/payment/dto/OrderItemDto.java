package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class OrderItemDto {
    private String itemName;
    private Integer amount;
    private Integer price;

    @Builder
    public OrderItemDto(String itemName, Integer amount, Integer price) {
        this.itemName = itemName;
        this.amount = amount;
        this.price = price;
    }
}
