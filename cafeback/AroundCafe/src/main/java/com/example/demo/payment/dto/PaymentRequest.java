package com.example.demo.payment.dto;

import com.example.demo.payment.entity.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PaymentRequest {
    private String itemInitName;
    private String exPaymentNo; // 외부 결제 ID
    private String paymentMethod; // 우선 KAKAO 고정
    private Integer totalQuantity;
    private Integer totalAmount;
    private Integer totalPointAmount;
    private Date paymentDate;
    private PaymentStatus paymentStatus;
    private List<OrderItemRequest> orderItems;
    private Long memNo;
    private Long cafeNo;


    public PaymentRequest(){}

    @Builder
    public PaymentRequest(String itemInitName, String exPaymentNo, String paymentMethod, Integer totalQuantity, Integer totalAmount, Integer totalPointAmount, Date paymentDate, ArrayList<OrderItemRequest> orderItems, Long memNo, Long cafeNo) {
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
