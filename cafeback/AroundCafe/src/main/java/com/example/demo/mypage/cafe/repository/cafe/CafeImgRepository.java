package com.example.demo.mypage.cafe.repository.cafe;

import com.example.demo.mypage.cafe.entity.CafeImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CafeImgRepository extends JpaRepository<CafeImg,Long> {
    @Query(value = "select * from cafe_img where cafe_no :cafeNo", nativeQuery = true)
    Optional<CafeImg> findByCafeNo(Long cafeNO);

    @Query(value = "select cafe_img from cafe_img where cafe_no :cafeNo and reg_date ", nativeQuery = true)
    List<String> findByCafeImg(Long cafeNO);

    @Query(value = "delete from cafe_img where cafe_no :cafeNo", nativeQuery = true)
    public void deleteByCafeNo(Long cafeNo);
}
