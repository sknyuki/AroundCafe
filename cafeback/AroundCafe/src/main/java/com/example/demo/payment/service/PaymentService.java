package com.example.demo.payment.service;

import com.example.demo.member.entity.Member;
import com.example.demo.payment.dto.PaymentDto;
import com.example.demo.payment.dto.PaymentResponse;
import com.example.demo.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    public void savePayment(PaymentDto PaymentDto);
    public List<PaymentResponse> getPaymentList(Member member);
    public List<Payment> getPayment(Member member);
    public void deletePayment(Long paymentNo);
}
