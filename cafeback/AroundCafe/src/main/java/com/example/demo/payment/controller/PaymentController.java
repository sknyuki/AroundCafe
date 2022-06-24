package com.example.demo.payment.controller;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.payment.dto.PaymentDto;
import com.example.demo.payment.dto.PaymentResponse;
import com.example.demo.payment.entity.Payment;
import com.example.demo.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PaymentService paymentService;
    // 3. 주문내역 수정(PUT) ---- 할일이 없을거 같음(환불시?)
    // 4. 주문내역 삭제(DELETE)

    @PostMapping
    public String savePayment(@Valid @RequestBody PaymentDto paymentDto) {
        log.info("접속");
        log.info(paymentDto.toString());
        paymentService.savePayment(paymentDto);
        return "잘됨";
    }

    @GetMapping("/{memNo}")
    public List<PaymentResponse> getPaymentList(@PathVariable Long memNo) {

        Member member = memberRepository.findByMemNo(memNo)
                .orElseThrow(() -> new UsernameNotFoundException("No user exists"));

        return paymentService.getPaymentList(member);
    }

    @DeleteMapping("/{paymentNo}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePayment(@PathVariable Long paymentNo) {
        paymentService.deletePayment(paymentNo);
    }
}