package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PaymentDto {
    private String itemInitName;
    private String exPaymentNo; // 외부 결제 ID
    private String paymentMethod; // 우선 KAKAO 고정
    private Long totalAmount;
    private Long totalPointAmount;
    private Boolean isCancelled = false;
    private List<OrderItemDto> orderItems = new ArrayList<>();
    private Long memNo;

    @Builder
    public PaymentDto(String itemInitName, String exPaymentNo, String paymentMethod, Long totalAmount, Long totalPointAmount, ArrayList<OrderItemDto> orderItems, Long memNo) {
        this.itemInitName = itemInitName;
        this.exPaymentNo = exPaymentNo;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.totalPointAmount = totalPointAmount;
        this.orderItems = orderItems;
        this.memNo = memNo;
    }
}
