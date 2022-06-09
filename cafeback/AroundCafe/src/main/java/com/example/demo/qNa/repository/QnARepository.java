package com.example.demo.qNa.repository;

import com.example.demo.qNa.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Long> {

    @Transactional
    @Query(value = "select * from qna where member_no = :membNo order by qna_no desc", nativeQuery = true)
    public List<QnA> findByMemberInfo(@Param("membNo") Long membNo);
}
