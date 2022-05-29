package com.example.demo.mypage.cafe.repository;

import com.example.demo.mypage.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    @Query(value = "select cafe_no from cafe order by cafe_no desc", nativeQuery = true)
    public Long findOne();
}
