package com.example.demo.payment.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.payment.entity.Payment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("select m from Payment m where m.member = :member")
    public List<Payment> findAllByMember(@Param("member") Member member);

    @Query("select m from Payment m where m.cafeNo = :cafeNo")
    public List<Payment> findAllByCafeNo(@Param("cafeNo") Long cafeNo);

    @Query("select m from Payment m where m.paymentNo = :paymentNo")
    public Optional<Payment> findByPaymentNo(@Param("paymentNo") Long paymentNo);

    @Transactional
    public void deleteByPaymentNo(@Param("paymentNo")Long paymentNo);
    @Modifying
    @Transactional
    @Query("delete from Payment p where p.paymentDate is null")
    public void deleteNullDate();
    @Query("select p from Payment p where p.paymentDate is null")
    List<Payment> findByNullDate();
}
