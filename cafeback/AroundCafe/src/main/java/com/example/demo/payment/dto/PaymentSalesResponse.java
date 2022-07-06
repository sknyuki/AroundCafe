package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class PaymentSalesResponse {
    private LocalDate date;
    private Integer total_quantity;
    private Integer total_amount;

    @Builder
    public PaymentSalesResponse(LocalDate date, int total_quantity, int total_amount){
        this.date = date;
        this.total_amount = total_amount;
        this.total_quantity= total_quantity;
    }
}
