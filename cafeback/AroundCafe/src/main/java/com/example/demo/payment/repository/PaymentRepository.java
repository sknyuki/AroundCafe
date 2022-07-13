package com.example.demo.payment.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.payment.dto.PaymentSalesMenuResponse;
import com.example.demo.payment.entity.Payment;
import com.example.demo.payment.entity.PaymentStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("select m from Payment m where m.member = :member order by m.paymentDate desc")
    public List<Payment> findAllByMember(@Param("member") Member member);

    @Query("select m from Payment m where m.paymentNo = :paymentNo ")
    public Optional<Payment> findByPaymentNo(@Param("paymentNo") Long paymentNo);

    @Query("select m from Payment m where m.cafeNo = :cafeNo order by m.paymentDate desc")
    public List<Payment> findAllByCafeNo(@Param("cafeNo") Long cafeNo);

    @Transactional
    @Query("update Payment p set p.member = null where p.member= :member")
    @Modifying
    public void setMemberNull(@Param("member") Member member);

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
    @Query("select distinct DATE_FORMAT(p.paymentDate,'%Y-%m-%d')as paymentDate from Payment p where p.cafeNo = :cafeNo order by p.paymentDate desc")
    public List<String> findByDay(Long cafeNo);



    @Query(value = "select sum(total_amount) from payment where cafe_no = :cafeNo and payment_date like %:date1%",nativeQuery = true)
    public Integer findBySum(Long cafeNo, String date1);

    @Query(value = "select * from payment where cafe_no = :cafeNo and payment_date like %:date%",nativeQuery = true)
    public List<Payment> findByCafeNoAndPaymentDateLike(Long cafeNo, Date date);

    @Query(value = "select o.itemName as itemName, sum(o.amount) as sum, count(o.orderItemNo) as count from OrderItem o where o.payment in " +
            "(select p.paymentNo from Payment p where cafeNo=:cafeNo) group by o.itemName order by sum(o.amount) desc")
    public List<PaymentSalesMenuResponse> findByMenuList(Long cafeNo);

    @Query(value = "select p.cafeNo as itemName, sum(p.totalAmount) as sum, count(p.paymentNo) as count from Payment p group by p.cafeNo order by sum(p.totalAmount) desc")
    public List<PaymentSalesMenuResponse> findByEachCafeSalesList();

    @Query("select m from Payment m join fetch m.member c where c.memNo= :memNo and m.paymentDate >=:date1 and m.paymentDate<=:date2")
            public List<Payment> findAllByMember(@Param("memNo") Long memNo, @Param("date1") Date date1, @Param("date2") Date date2);
}
