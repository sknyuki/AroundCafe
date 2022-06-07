package com.example.demo.mypage.cafe.repository.basket;

import com.example.demo.mypage.cafe.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query(value = "select * from Basket where mem_no : memNo order by shop_no desc", nativeQuery = true)
    public List<Basket> findByMemNo(Long memNo);
}

