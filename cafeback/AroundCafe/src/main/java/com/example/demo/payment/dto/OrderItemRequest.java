package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private String itemName;
    private Integer quantity;
    private Integer amount;
    private Long cafeMenuNo;

    public OrderItemRequest(){}
    @Builder
    public OrderItemRequest(String itemName, Integer quantity, Integer amount, Long cafeMenuNo) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
        this.cafeMenuNo = cafeMenuNo;
    }
}
