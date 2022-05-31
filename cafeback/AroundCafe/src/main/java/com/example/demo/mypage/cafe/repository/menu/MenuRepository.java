package com.example.demo.mypage.cafe.repository.menu;

import com.example.demo.mypage.cafe.entity.CafeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<CafeMenu, Long> {
    @Query(value = "select * from cafe_menu where cafe_no : cafeNo order by menu_no desc", nativeQuery = true)
    public List<CafeMenu> findByCafeNo(Long cafeNo);
}
