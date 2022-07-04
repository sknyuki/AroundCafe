package com.example.demo.payment.controller;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.payment.dto.PaymentRequest;
import com.example.demo.payment.dto.PaymentResponse;
import com.example.demo.payment.service.PaymentService;
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
    private final MemberRepository memberRepository;
    private final PaymentService paymentService;
    // 3. 주문내역 수정(PUT) ---- 할일이 없을거 같음(환불시?)
    // 4. 주문내역 삭제(DELETE)

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Long savePayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.savePayment(paymentRequest);
    }

    @GetMapping("/{paymentNo}")
    public PaymentResponse getPayment(@PathVariable Long paymentNo) {
        return paymentService.getPaymentResponse(paymentNo);
    }

    @GetMapping("/list/{memNo}")
    public List<PaymentResponse> getPaymentList(@PathVariable Long memNo) {

        Member member = memberRepository.findByMemNo(memNo)
                .orElseThrow(() -> new UsernameNotFoundException("No user exists"));

        return paymentService.getAllPaymentResponse(member);
    }

    @GetMapping("/cafe/{cafeNo}")
    public List<PaymentResponse> getPaymentListByCafeNo(@PathVariable Long cafeNo) {
        return paymentService.getAllPaymentResponse(cafeNo);
    }

    @DeleteMapping("/{paymentNo}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePayment(@PathVariable Long paymentNo) {
        paymentService.deletePayment(paymentNo);
    }

    @PutMapping("/{paymentNo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void confirmPayment(@PathVariable Long paymentNo) {
        paymentService.paymentConfirm(paymentNo);
    }
}