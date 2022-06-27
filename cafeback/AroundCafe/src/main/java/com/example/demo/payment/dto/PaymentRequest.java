package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PaymentRequest {
    private String itemInitName;
    private String exPaymentNo; // 외부 결제 ID
    private String paymentMethod; // 우선 KAKAO 고정
    private Long totalQuantity;
    private Long totalAmount;
    private Long totalPointAmount;
    private Date paymentDate;
    private Boolean isCancelled = false;

    private List<OrderItemRequest> orderItems;
    private Long memNo;

    private Long cafeNo;

    @Builder
    public PaymentRequest(String itemInitName, String exPaymentNo, String paymentMethod, Long totalQuantity, Long totalAmount, Long totalPointAmount, Date paymentDate, ArrayList<OrderItemRequest> orderItems, Long memNo, Long cafeNo) {
        this.itemInitName = itemInitName;
        this.exPaymentNo = exPaymentNo;
        this.paymentMethod = paymentMethod;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.totalPointAmount = totalPointAmount;
        this.orderItems = orderItems;
        this.paymentDate = paymentDate;
        this.memNo = memNo;
        this.cafeNo = cafeNo;
    }
}
