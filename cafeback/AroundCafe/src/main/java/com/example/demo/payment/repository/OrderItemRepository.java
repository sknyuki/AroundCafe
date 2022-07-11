package com.example.demo.payment.repository;

import com.example.demo.payment.entity.OrderItem;
import com.example.demo.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Modifying
    @Transactional
    @Query("delete from OrderItem p where p.payment =:payment")
    void deleteByPayment(Payment payment);

    @Query(value = "select count(*) from order_item where payment in" +
            " (select payment_no from payment where cafe_no =:cafeNo and payment_date like %:date1%) group by payment;",nativeQuery = true)
    Integer findByCount(Long cafeNo, String date1);

    @Transactional
    @Query("select o from OrderItem o join fetch o.payment p where p.cafeNo =:cafeNo order by p.paymentDate desc")
    List<OrderItem> findByAllFromCafeNo(Long cafeNo);
    @Query("select count(o.orderItemNo) from OrderItem o where o.payment in (select p from Payment p where p.cafeNo = :cafeNo)")
    Integer findByCountFromCafeNo(Long cafeNo);
}
