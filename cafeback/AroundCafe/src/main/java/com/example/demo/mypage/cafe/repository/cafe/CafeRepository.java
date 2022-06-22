package com.example.demo.mypage.cafe.repository.cafe;

import com.example.demo.mypage.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    @Transactional
    @Query(value = "select * from cafe where member_no = :membNo",nativeQuery = true)
    Optional<Cafe> findByMemberNo(@Param("membNo") Long membNo);

    @Transactional
    @Query(value = "select * from cafe where member_no = :membNo",nativeQuery = true)
    Optional<Cafe> findByCafeNo(@Param("membNo") Long membNo);
}
