package com.example.demo.payment.service;

import com.example.demo.member.entity.Member;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.payment.dto.PaymentRequest;
import com.example.demo.payment.dto.PaymentResponse;
import com.example.demo.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PaymentService {
    public Long savePayment(PaymentRequest PaymentRequest);
    public List<PaymentResponse> getAllPaymentResponse(Member member);
    public List<PaymentResponse> getAllPaymentResponse(Long cafeNo);
    public PaymentResponse getPaymentResponse(Long paymentNo);
    public void paymentConfirm(Long paymentNo);
    public void deletePayment(Long paymentNo);
}
