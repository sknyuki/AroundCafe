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
    private String imageUrl;

    @Builder
    public OrderItemResponse(String itemName, Integer quantity, Integer amount, String imageUrl) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
        this.imageUrl = imageUrl;
    }
}
