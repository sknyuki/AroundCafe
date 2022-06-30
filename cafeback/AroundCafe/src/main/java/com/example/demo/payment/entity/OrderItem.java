package com.example.demo.payment.entity;

import com.example.demo.common.entity.BaseDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemNo;
    private String itemName; // 메뉴명
    private Integer quantity; // 수량
    private Integer amount;  // 제품 총가격
    private Long cafeMenuNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment")
    private Payment payment;

    @Builder
    public OrderItem(String itemName, Integer quantity, Integer amount, Long cafeMenuNo, Payment payment) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
        this.cafeMenuNo = cafeMenuNo;
        this.payment = payment;
    }
}
