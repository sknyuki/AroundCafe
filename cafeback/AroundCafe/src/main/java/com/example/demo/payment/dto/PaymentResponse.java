package com.example.demo.payment.dto;

import com.example.demo.payment.entity.PaymentStatus;
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
    private Integer totalQuantity;
    private Integer totalAmount;
    private Integer totalPointAmount;
    private Date paymentDate;
    private PaymentStatus paymentStatus;
    private List<OrderItemResponse> orderItems;
    private Long cafeNo;
    private Long memNo;

    @Builder
    public PaymentResponse(Long paymentNo, String itemInitName, String paymentMethod, Integer totalQuantity, Integer totalAmount, Integer totalPointAmount, Date paymentDate, PaymentStatus paymentStatus, ArrayList<OrderItemResponse> orderItems, Long cafeNo, Long memNo, String memNick) {
        this.paymentNo = paymentNo;
        this.itemInitName = itemInitName;
        this.paymentMethod = paymentMethod;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.totalPointAmount = totalPointAmount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.orderItems = orderItems;
        this.cafeNo = cafeNo;
        this.memNo = memNo;
    }
}
