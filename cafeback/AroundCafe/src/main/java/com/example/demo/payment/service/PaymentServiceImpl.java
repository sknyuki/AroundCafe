package com.example.demo.payment.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.payment.dto.OrderItemDto;
import com.example.demo.payment.dto.PaymentDto;
import com.example.demo.payment.dto.PaymentResponse;
import com.example.demo.payment.entity.OrderItem;
import com.example.demo.payment.entity.Payment;
import com.example.demo.payment.map.OrderItemMapStruct;
import com.example.demo.payment.map.PaymentResponseMapStruct;
import com.example.demo.payment.repository.OrderItemRepository;
import com.example.demo.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void savePayment(PaymentDto paymentDto) {
        log.info("메소드 접속");
        log.info(paymentDto.getMemNo().toString());
        Member member = memberRepository.findByMemNo(paymentDto.getMemNo()).get();
        log.info("멤버 생성, member: " + member.toString());
        List<OrderItemDto> orderItemDto = paymentDto.getOrderItems();
        log.info("orderItems 생성, orderitems: " + orderItemDto);

        Payment payment = Payment.builder()
                .exPaymentNo(paymentDto.getExPaymentNo())
                .paymentMethod(paymentDto.getPaymentMethod())
                .totalAmount(paymentDto.getTotalAmount())
                .totalPointAmount(paymentDto.getTotalPointAmount())
                .member(member)
                .build();

        paymentRepository.save(payment);

        for(int i = 0; i < paymentDto.getOrderItems().size(); i++) {
            OrderItem orderItem = OrderItem.builder()
                    .itemName(orderItemDto.get(i).getItemName())
                    .amount(orderItemDto.get(i).getAmount())
                    .price(orderItemDto.get(i).getPrice())
                    .payment(payment)
                    .build();
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    @Transactional
    public List<PaymentResponse> getPaymentList(Member member) {
        List<Payment> paymentList =  paymentRepository.findAllByMember(member);
        List<PaymentResponse> paymentDtoList = new ArrayList<>();
        for(Payment payment : paymentList) {
            PaymentResponse paymentResponse = PaymentResponseMapStruct.instance.toDto(payment);
            List<OrderItemDto> orderItemList = new ArrayList<>();
            for(OrderItem orderItem : payment.getOrderItem()) {
                OrderItemDto orderItemDto = OrderItemMapStruct.instance.toDto(orderItem);
                orderItemList.add(orderItemDto);
            }
            paymentResponse.setOrderItems(orderItemList);
            paymentDtoList.add(paymentResponse);
        }

        return paymentDtoList;
    }

    @Override
    public List<Payment> getPayment(Member member) {
        return paymentRepository.findAllByMember(member);
    }

    @Override
    public void deletePayment(Long paymentNo) {
        paymentRepository.deleteByPaymentNo(paymentNo);
    }
}
