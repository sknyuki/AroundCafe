package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PaymentResponse {
    private Long paymentNo;
    private String itemInitName;
    private String paymentMethod; // 우선 KAKAO 고정
    private Long totalQuantity;
    private Long totalAmount;
    private Long totalPointAmount;
    private Date paymentDate;
    private Boolean isCancelled;
    private List<OrderItemResponse> orderItems;
    private Long cafeNo;

    @Builder
    public PaymentResponse(Long paymentNo, String itemInitName, String paymentMethod, Long totalQuantity, Long totalAmount, Long totalPointAmount, Date paymentDate, Boolean isCancelled, ArrayList<OrderItemResponse> orderItems, Long cafeNo) {
        this.paymentNo = paymentNo;
        this.itemInitName = itemInitName;
        this.paymentMethod = paymentMethod;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.totalPointAmount = totalPointAmount;
        this.paymentDate = paymentDate;
        this.isCancelled = isCancelled;
        this.orderItems = orderItems;
        this.cafeNo = cafeNo;
    }
}
