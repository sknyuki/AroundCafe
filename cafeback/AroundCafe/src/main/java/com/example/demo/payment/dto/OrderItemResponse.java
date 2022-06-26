package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private String itemName;
    private Integer quantity;
    private Integer amount;

    @Builder
    public OrderItemResponse(String itemName, Integer quantity, Integer amount) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
    }
}
