package com.example.demo.payment.repository;

import com.example.demo.payment.entity.OrderItem;
import com.example.demo.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Modifying
    @Transactional
    @Query("delete from OrderItem p where p.payment =:payment")
    void deleteByPayment(Payment payment);
}
