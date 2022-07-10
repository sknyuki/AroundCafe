package com.example.demo.payment.service;

import com.example.demo.member.entity.Member;
import com.example.demo.payment.dto.*;
import com.example.demo.payment.entity.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    public Long savePayment(PaymentRequest PaymentRequest);
    public List<PaymentResponse> getAllPaymentResponse(Member member);
    public List<PaymentResponse> getAllPaymentResponse(Long cafeNo);
    public PaymentResponse getPaymentResponse(Long paymentNo);
    public void paymentConfirm(Long paymentNo);
    public void deletePayment(Long paymentNo);
    public void modifyPaymentStatus(Long paymentNo, PaymentStatus paymentStatus);

    public List<PaymentSalesResponse> getCafeSalesList(Long cafeNo);

    public List<PaymentSalesDetailResponse> getPaymentSalesDetailList(Long cafeNo, String date);

    public List<PaymentSalesMenuResponse1> getPaymentSalesMenuList(Long cafeNo);

    public List<PaymentResponse> getPaymentByDate(Long memNo, Long date1, Long date2);

    public List<PaymentSalesResponse> getPaymentCafeSalesList();

    public List<PaymentSalesDetailResponse> getPaymentCafeSalesDetailListByAdmin(Long cafeNo);
}
