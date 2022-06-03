package com.example.demo.mypage.cafe.repository.basket;

import com.example.demo.mypage.cafe.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface basketRepository extends JpaRepository<Basket, Long> {
}

