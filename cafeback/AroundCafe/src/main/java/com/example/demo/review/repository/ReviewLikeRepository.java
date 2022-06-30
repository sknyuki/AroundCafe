
package com.example.demo.review.repository;

import com.example.demo.member.entity.Member;
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
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {


    // Optional<ReviewLike> findByReviewAndWriter (Review review, String writer);

    //@Transactional
    //@Query("select m from Member m and select r from Review r")
    //Optional<ReviewLike> findByMemberAndReview (Member member, Review review);

    /*
    @Transactional
    @Query("select * from member (where member_no = :member) and select * from review (where review_no = :review)")
    Optional<ReviewLike> findByMemberAndReview (Member member, Review review);
     */

    @Transactional
    @Query(value = "select * from review_like where review_no = :reviewNo and member_no = :membNo", nativeQuery = true)
    Optional<ReviewLike> findReviewLikeReviewNoAndMemberNo(@Param("reviewNo") Long reviewNo, @Param("membNo") Long membNo);

    @Transactional
    @Query(value = "select * from review_like where review_no = ?1", nativeQuery = true)
    List<ReviewLike> findByReviewNo(@Param("reviewNo") Long reviewNo);

    @Query(value = "select * from review_like where member_no = ?1", nativeQuery = true)
    List<ReviewLike> findByMemberNo(@Param("membNo") Long membNo);


    @Transactional
    @Query(value="select l from review_like l join l.review cb where cb.reviewNo = :reviewNo", nativeQuery = true)
    List<ReviewLike> findAllLikesReviewNo(@Param("reviewNo") Long reviewNo);



    @Transactional
    @Query(value="select l from review_like l join l.member cd where cd.memberNo = :membNo", nativeQuery = true)
    List<ReviewLike> findAllLikesMemberNo(@Param("membNo") Long membNo);


    List<ReviewLike> findByReview(Review review);
}





