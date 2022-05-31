package com.example.demo.mypage.cafe.service.menu;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.mypage.cafe.repository.menu.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuRepository repository;

    @Autowired
    CafeRepository cafeRepository;


    @Override
    public void includeImgSave(CafeMenuDto info, String originalFilename) {
        String file = info.getCafe_name()+"." +originalFilename;

        log.info("file: " +file);
        log.info("cafe no : " +info.getCafe_no());

        Optional<Cafe> findCafe = cafeRepository.findById(info.getCafe_no());
        Cafe cafe = findCafe.get();

        CafeMenu cafeMenu = new CafeMenu(info.getMenu_name(), info.getMenu_price(), file, info.getMenu_content(), cafe);
        repository.save(cafeMenu);

    }

    @Override
    public void exceptImgSave(CafeMenuDto info) {
        log.info("cafe no : " +info.getCafe_no());

        Optional<Cafe> findCafe = cafeRepository.findById(info.getCafe_no());
        Cafe cafe = findCafe.get();

        CafeMenu cafeMenu = new CafeMenu(info.getMenu_name(), info.getMenu_price(), info.getMenu_content(), cafe);
        repository.save(cafeMenu);
    }
}
