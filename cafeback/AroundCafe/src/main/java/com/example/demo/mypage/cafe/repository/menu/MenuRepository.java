package com.example.demo.mypage.cafe.repository.menu;

import com.example.demo.mypage.cafe.entity.CafeMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<CafeMenu, Long> {
}
