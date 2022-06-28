package com.example.demo.mypage.cafe.repository.cafeLike;

import com.example.demo.mypage.cafe.entity.CafeLike;
import com.example.demo.review.entity.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface CafeLikeRepository extends JpaRepository<CafeLike, Long> {

    @Transactional
    @Query(value = "select * from cafe_like where cafe_no = :cafeNo and member_no = :membNo", nativeQuery = true)
    Optional<CafeLike> findCafeLikeCafeNoAndCafeNo(@Param("cafeNo") Long cafeNo, @Param("membNo") Long membNo);

    @Transactional
    @Query(value = "select * from cafe_like where cafe_no = ?1", nativeQuery = true)
    List<CafeLike> findByCafeNo(@Param("cafeNo") Long cafeNo);

    @Query(value = "select * from cafe_like where member_no = ?1", nativeQuery = true)
    List<CafeLike> findByMemberNo(@Param("membNo") Long membNo);


    @Transactional
    @Query(value="select l from cafe_like l join l.cafe cb where cb.cafeNo = :cafeNo", nativeQuery = true)
    List<CafeLike> findAllLikesCafeNo(@Param("cafeNo") Long cafeNo);



    @Transactional
    @Query(value="select l from cafe_like l join l.member cd where cd.memberNo = :membNo", nativeQuery = true)
    List<CafeLike> findAllLikesMemberNo(@Param("membNo") Long membNo);

    @Transactional
    @Query(value = "select * from cafe_like where cafe_no = :searchId", nativeQuery = true)
    public List<CafeLike> findIdList(String searchId);


}
