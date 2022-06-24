package com.example.demo.payment.entity;

import com.example.demo.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNo;
    private String itemInitName;
    private String exPaymentNo; // 외부 결제 ID
    private String paymentMethod; // 우선 KAKAO 고정
    private Long totalAmount;
    private Long totalPointAmount;
    private Boolean isCancelled;

    @OneToMany(mappedBy = "payment")
    private List<OrderItem> orderItem = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @Builder
    public Payment(String itemInitName, String exPaymentNo, String paymentMethod, Long totalAmount, Long totalPointAmount, Boolean isCancelled, ArrayList<OrderItem> orderItem, Member member) {
        this.itemInitName = itemInitName;
        this.exPaymentNo = exPaymentNo;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.totalPointAmount = totalPointAmount;
        this.isCancelled = isCancelled;
        this.orderItem = orderItem;
        this.member = member;
    }
}
