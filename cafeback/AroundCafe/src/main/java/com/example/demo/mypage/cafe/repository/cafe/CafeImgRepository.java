package com.example.demo.mypage.cafe.repository.cafe;

import com.example.demo.mypage.cafe.entity.CafeImgTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CafeImgRepository extends JpaRepository<CafeImgTable,Long> {
    @Query(value = "select count(*) from cafe_img_table where cafe_no = :cafe_no", nativeQuery = true)
    Optional<Integer> findByCafe_no(@Param("cafe_no") Long cafe_no);

    @Query(value = "select * from cafe_img_table where cafe_no :cafe_no", nativeQuery = true)
    List<CafeImgTable> findCafe(@Param("cafe_no") Long cafe_no);

    @Query(value = "delete from cafe_img_table where cafe_no :cafeNo", nativeQuery = true)
    public void deleteByCafeNo(Long cafeNo);
}
