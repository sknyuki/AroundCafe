package com.example.demo.payment.entity;

public enum PaymentStatus {
    PAYMENT_READY, // 결제 대기
    PAYMENT_CONFIRMED, // 결제 확정
    CAFE_READY, // 준비중
    CAFE_ORDER_COMPLETE, // 픽업 완료
    PAYMENT_CANCELED // 결제 취소
}
