package com.example.demo.payment.dto;

import com.example.demo.payment.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Getter
public class PaymentSalesDetailResponse {
    private Long paymentNo;
    private String itemName;
    private Integer quantity;
    private Integer amount;
    private Date paymentDate;
    private String memNick;
    private String memImg;

    @Builder
    public PaymentSalesDetailResponse(Date paymentDate,
                                      String memNick, String memImg,String itemName,Long paymentNo, Integer quantity, Integer amount){


        this.paymentDate = paymentDate;
        this.memNick = memNick;
        this.memImg = memImg;
        this.itemName = itemName;
        this.amount = amount;
        this.quantity = quantity;
        this.paymentNo = paymentNo;

    }
}
