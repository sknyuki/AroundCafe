package com.example.demo.payment.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.payment.entity.Payment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("select m from Payment m where m.member = :member")
    public List<Payment> findAllByMember(@Param("memNo") Member member);

    public void deleteByPaymentNo(Long paymentNo);
}
