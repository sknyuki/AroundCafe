package com.example.demo.review.repository;


import com.example.demo.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Transactional
    @Query(value ="select * from review where review_no = :memNo", nativeQuery = true)
    List<Review> findReviewByMemberNo(Long memNo);

    @Transactional
    @Query(value ="select * from review where review_no = :cafeNum", nativeQuery = true)
    List<Review> findAllReviewByCafe(Long cafeNum);

    @Transactional
    @Query(value ="select * from review where review_no = :reviewNo", nativeQuery = true)
    Optional<Review> findByReviewNo(Long reviewNo);

}