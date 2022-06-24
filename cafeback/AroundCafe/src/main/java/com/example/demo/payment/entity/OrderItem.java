package com.example.demo.payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemNo;
    private String itemName;
    private Integer amount;
    private Integer price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment")
    private Payment payment;

    @Builder
    public OrderItem(Long orderItemNo, String itemName, Integer amount, Integer price, Payment payment) {
        this.orderItemNo = orderItemNo;
        this.itemName = itemName;
        this.amount = amount;
        this.price = price;
        this.payment = payment;
    }
}
