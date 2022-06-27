package com.example.demo.payment.entity;

import com.example.demo.common.entity.BaseDateTime;
import com.example.demo.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Payment extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNo;
    private String itemInitName;
    private String exPaymentNo; // 외부 결제 ID
    private String paymentMethod; // 우선 KAKAO 고정
    private Long totalQuantity;
    private Long totalAmount; // 총 가격
    private Long totalPointAmount; // 총 포인트 사용
    private Date paymentDate;
    private Date expTime;
    private Boolean isCancelled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payment", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItem = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    private Long cafeNo;

    @Builder
    public Payment(String itemInitName, String exPaymentNo, String paymentMethod, Long totalQuantity, Long totalAmount, Long totalPointAmount, Date paymentDate, Date expTime, Boolean isCancelled, HashSet<OrderItem> orderItem, Member member, Long cafeNo) {
        this.itemInitName = itemInitName;
        this.exPaymentNo = exPaymentNo;
        this.paymentMethod = paymentMethod;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.totalPointAmount = totalPointAmount;
        this.paymentDate = paymentDate;
        this.expTime = expTime;
        this.isCancelled = isCancelled;
        this.orderItem = orderItem;
        this.member = member;
        this.cafeNo = cafeNo;
    }
}
