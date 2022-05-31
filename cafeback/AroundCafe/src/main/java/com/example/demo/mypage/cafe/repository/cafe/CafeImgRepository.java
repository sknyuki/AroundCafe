package com.example.demo.mypage.cafe.repository.cafe;

import com.example.demo.mypage.cafe.entity.CafeImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeImgRepository extends JpaRepository<CafeImg,Long> {
    Optional<CafeImg> findByCafe_no(Integer cafeNO);

    //@Query(value = "select cafe_img from cafe_img where cafe_no : cafeNo")
    List<String> findByCafe_img(Integer cafeNO);

    //@Query(value = "delete from cafe_img where cafe_no : cafeNo")
    public void deleteByCafe_no(Integer cafeNO);
}
