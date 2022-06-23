package com.example.demo.mypage.cafe.repository.menu;

import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MenuRepository extends JpaRepository<CafeMenu, Long> {
    @Transactional
    @Query(value = "select * from cafe_menu where cafe_no = :cafeNo order by menu_no desc", nativeQuery = true)
    public List<CafeMenu> findByCafeNo(@Param("cafeNo") Long cafeNo);

    @Transactional
    @Query(value = "select count(*) from cafe_menu where signature = 1", nativeQuery = true)
    public Integer countSignature();

    @Transactional
    @Query(value = "select * from cafe_menu where signature = 1", nativeQuery = true)
    public List<CafeMenu> findBySignatureTrue();

    @Transactional
    @Query(value = "select * from cafe_menu where sold_out = 1", nativeQuery = true)
    public List<CafeMenu> findBySoldTrue();

    @Transactional
    @Modifying
    @Query(value = "delete from cafe_menu where cafe_no = cafe_no", nativeQuery = true)
    public void deleteByCafeNo(@Param("cafe_no") Long cafe_no);

    @Transactional
    @Query(value = "select * from cafe_menu where menu_name like %:menuName% and cafe_no = :cafeNo order by menu_no desc",nativeQuery = true)
    List<CafeMenu> searchList(@Param("cafeNo") Long cafeNo, @Param("menuName") String menuName);

    //List<CafeMenu> findByCafeMenuContainingIgnoreCase(Integer cafe_no, String menu_name);
}
