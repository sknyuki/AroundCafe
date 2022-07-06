package com.example.demo.payment.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.payment.dto.PaymentSalesMenuResponse1;
import com.example.demo.payment.entity.Payment;
import com.example.demo.payment.entity.PaymentStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Modifying
    @Query("update Payment m set m.paymentStatus = :paymentStatus where m.paymentNo = :paymentNo")
    public void updatePaymentStatus(Long paymentNo, PaymentStatus paymentStatus);
    @Query("select distinct DATE_FORMAT(p.paymentDate,'%Y-%m-%d')as paymentDate from Payment p where p.cafeNo = :cafeNo ")
    public List<String> findByDay(Long cafeNo);

    @Query(value = "select count(*) from payment where cafe_no = :cafeNo and payment_date like %:date1%",nativeQuery = true)
    public Integer findByCount(Long cafeNo, LocalDate date1);

    @Query(value = "select sum(total_amount) from payment where cafe_no = :cafeNo and payment_date like %:date1%",nativeQuery = true)
    public Integer findBySum(Long cafeNo, LocalDate date1);

    @Query(value = "select * from payment where cafe_no = :cafeNo and payment_date like %:date%",nativeQuery = true)
    public List<Payment> findByCafeNoAndPaymentDateLike(Long cafeNo, Date date);

    @Query(value = "select o.itemName as itemName, sum(o.amount) as sum, count(o.orderItemNo) as count from OrderItem o where o.payment in " +
            "(select p.paymentNo from Payment p where cafeNo=:cafeNo) group by o.itemName")
    public List<PaymentSalesMenuResponse1> findByMenuList(Long cafeNo);
}
