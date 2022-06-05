package com.example.demo.mypage.cafe.service.basket;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.mypage.cafe.repository.menu.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class basketServiceImpl implements basketService {

    @Autowired
    basketRepository repository;

    @Autowired
    CafeRepository cafeRepository;


    @Transactional
    @Override
    public void includeImgSave(CafeMenuDto info, String originalFilename) {
        String file = info.getCafe_name()+"." +originalFilename;

        log.info("file: " +file);
        log.info("cafe no : " +info.getCafe_no());

//        Optional<Cafe> findCafe = cafeRepository.findById(info.getCafe_no());
//        Cafe cafe = findCafe.get();

        CafeMenu cafeMenu = new CafeMenu(info.getMenu_name(), info.getMenu_price(), file, info.getMenu_content());
        repository.save(cafeMenu);

    }

    @Transactional
    @Override
    public void exceptImgSave(CafeMenuDto info) {
        log.info("cafe no : " +info.getCafe_no());

//        Optional<Cafe> findCafe = cafeRepository.findById(info.getCafe_no());
//        Cafe cafe = findCafe.get();

        CafeMenu cafeMenu = new CafeMenu(info.getMenu_name(), info.getMenu_price(), info.getMenu_content());
        repository.save(cafeMenu);
    }



    @Transactional
    @Override
    public List<CafeMenu> list(Integer membNo) {
        Optional<Cafe> findCafeNo = cafeRepository.findByMemberNo(Long.valueOf(membNo));
        Cafe cafe = findCafeNo.get();
        Long CafeNo = cafe.getCafeNo();

        return repository.findByCafeNo(CafeNo);
    }

    @Transactional
    @Override
    public void delete(Integer menuNo) throws IOException {
        Optional<CafeMenu> findMenu = repository.findById(Long.valueOf(menuNo));
        CafeMenu menu = findMenu.get();

        if(menu.getMenu_img() != null) {
            Path file = Paths.get("../../cafefront/around_cafe/src/asserts/cafe/cafeMenu" + menu.getMenu_img());
            Files.delete(file);
        }

        repository.deleteById(Long.valueOf(menuNo));
    }

    @Transactional
    @Override
    public List<CafeMenu> list1() {
        log.info("test get list menu");
        return repository.findAll(Sort.by(Sort.Direction.ASC,"menu_no"));
    }

}
