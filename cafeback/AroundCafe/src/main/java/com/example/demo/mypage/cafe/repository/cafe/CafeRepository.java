package com.example.demo.mypage.cafe.repository.cafe;

import com.example.demo.mypage.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    @Transactional
    @Query(value = "select * from cafe where cafe_no :memNo",nativeQuery = true)
    Optional<Cafe> findByMemberNo(Long memNo);

}
