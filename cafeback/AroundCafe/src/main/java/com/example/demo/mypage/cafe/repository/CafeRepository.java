package com.example.demo.mypage.cafe.repository;

import com.example.demo.mypage.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    @Query(value = "select * from cafe order by cafe_no desc", nativeQuery = true)
    Optional<Cafe> findOne();
}
