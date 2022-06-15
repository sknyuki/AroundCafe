
package com.example.demo.review.repository;

import com.example.demo.review.entity.Review;
import com.example.demo.review.entity.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface  ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {


   // Optional<ReviewLike> findByReviewAndWriter (Review review, String writer);

    @Transactional
    @Query(value = "select * from review_like where review_no in(select review_no from review where review_no = :reviewNo)", nativeQuery = true)
    List<ReviewLike> findByReviewNo(@Param("reviewNo") Long reviewNo);


}




