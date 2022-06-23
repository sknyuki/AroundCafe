package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PaymentResponse {
    private String itemInitName;
    private String paymentMethod; // 우선 KAKAO 고정
    private Long totalAmount;
    private Long totalPointAmount;
    private Boolean isCancelled = false;
    private List<OrderItemDto> orderItems = new ArrayList<>();

    @Builder
    public PaymentResponse(String itemInitName, String paymentMethod, Long totalAmount, Long totalPointAmount, ArrayList<OrderItemDto> orderItems) {
        this.itemInitName = itemInitName;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.totalPointAmount = totalPointAmount;
        this.orderItems = orderItems;
    }
}
