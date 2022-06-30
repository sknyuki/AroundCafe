package com.example.demo.mypage.cafe.service.menu;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.mypage.cafe.repository.menu.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
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
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuRepository repository;

    @Autowired
    CafeRepository cafeRepository;


    @Transactional
    @Override
    public void includeImgSave(CafeMenuDto info, String fileName) {

        log.info("file: " +fileName);
        log.info("memb no : " +info.getMemNo());

        Cafe cafe = cafeRepository.findByMemberNo(Long.valueOf(info.getMemNo())).orElseGet(null);

        CafeMenu cafeMenu = CafeMenu.builder()
                .menu_name(info.getMenu_name())
                .menu_price(info.getMenu_price())
                .file(fileName)
                .menu_content(info.getMenu_content())
                .per_menu_quantity(info.getPer_menu_quantity())
                .per_menu_total_price(info.getPer_menu_total_price())
                .cafe(cafe)
                .build();

        repository.save(cafeMenu);

    }

    @Transactional
    @Override
    public void exceptImgSave(CafeMenuDto info) {
        log.info("cafe no : " +info.getCafe_no());

        Cafe cafe = cafeRepository.findByMemberNo(Long.valueOf(info.getMemNo())).orElseGet(null);

        CafeMenu cafeMenu = CafeMenu.builder()
                .menu_name(info.getMenu_name())
                .menu_price(info.getMenu_price())
                .menu_content(info.getMenu_content())
                .per_menu_quantity(info.getPer_menu_quantity())
                .per_menu_total_price(info.getPer_menu_total_price())
                .cafe(cafe)
                .build();

        repository.save(cafeMenu);
    }

    @Transactional
    @Override
    public void includeImgModify(CafeMenu info, String menu_img, Integer memNo) throws IOException {

        log.info("include img modify");
        log.info("file: " +menu_img);
        log.info("member no : " + memNo);

        Cafe cafe = cafeRepository.findByMemberNo(Long.valueOf(memNo)).orElseGet(null);
        CafeMenu cafeMenu = repository.findById(info.getMenu_no()).orElseGet(null);

        if(cafeMenu.getMenu_img() != null) {
            Path filePath = Paths.get("../../cafefront/around_cafe/src/assets/cafe/cafeMenu/" + cafeMenu.getMenu_img());
            Files.delete(filePath);
            log.info("file delete complete");
        }

        info.setMenu_img(menu_img);
        info.setCafe(cafe);

        repository.save(info);
        log.info("!!!modify ok!!!!");
    }

    @Transactional
    @Override
    public void exceptImgModify(CafeMenu info, Integer memNo) {
        log.info("except img modify");

        Cafe cafe = cafeRepository.findByMemberNo(Long.valueOf(memNo)).orElseGet(null);
        CafeMenu cafeMenu = repository.findById(info.getMenu_no()).orElseGet(null);

        if(cafeMenu.getMenu_img() != null) {
            info.setMenu_img(cafeMenu.getMenu_img());
        }

        log.info("!!!modify ok!!!!");

        info.setCafe(cafe);
        repository.save(info);

    }

    @Transactional
    @Override
    public List<CafeMenu> list(Integer cafeNo) {
        Cafe cafe = cafeRepository.findById(Long.valueOf(cafeNo)).orElseGet(null);
        log.info("cafe name:" +cafe.getCafe_name());
        Long CafeNo = cafe.getCafeNo();
        log.info("cafe no = " + CafeNo);

        return repository.findByCafeNo(CafeNo);
    }

    @Transactional
    @Override
    public void delete(Integer menuNo) throws IOException {
        CafeMenu menu = repository.findById(Long.valueOf(menuNo)).orElseGet(null);
        log.info("cafe name" +menu.getMenu_name());

        if(menu.getMenu_img() != null) {
            Path file = Paths.get("../../cafefront/around_cafe/src/assets/cafe/cafeMenu/" + menu.getMenu_img());
            Files.delete(file);
            log.info("img is deleted!!");
        }

        repository.deleteById(Long.valueOf(menuNo));
        log.info("delete menu complete!");
    }

    @Transactional
    @Override
    public List<CafeMenu> list1() {
        log.info("test get list menu");
        return repository.findAll();
    }

    @Transactional
    @Override
    public String changeSignature(Integer menuNo) {
        CafeMenu menu = repository.findById(Long.valueOf(menuNo)).orElseGet(null);
        Long checkCafeNo = menu.getCafe().getCafeNo();
        Integer countSignature = repository.countSignature(Long.valueOf(checkCafeNo));
        log.info("check cafe no : " + checkCafeNo+", count signature : " +countSignature);


        if(menu.getSignature() == true){
            menu.setSignature(false);
            repository.save(menu);
            return "시그니처를 해제했습니다.";
        }else {
            if (countSignature >=2){
                return "시그니처가 두개가 넘었습니다.";
            }else {
                menu.setSignature(true);
                repository.save(menu);
                return "시그니처가 등록되었습니다.";
            }
        }
        
    }


    @Override
    public String changeSoldOut(Integer menuNo) {
        CafeMenu menu = repository.findById(Long.valueOf(menuNo)).orElseGet(null);

        if(menu.getSold_out() == true) {
            menu.setSold_out(false);
            repository.save(menu);
            return "솔드아웃 메뉴 취소하였습니다..";
        }else
            menu.setSold_out(true);
            repository.save(menu);
            return "솔드아웃 메뉴로 등록되었습니다.";
        
    }

    @Transactional
    @Override
    public List<CafeMenu> soldList() {
        log.info("sold out list");
        return repository.findBySoldTrue();
    }

    @Transactional
    @Override
    public List<CafeMenu> findMenu(Integer memNo, String menu_name) {
        Cafe cafe = cafeRepository.findByMemberNo(Long.valueOf(memNo)).orElseGet(null);
        List<CafeMenu> cafeMenus = repository.searchList(Long.valueOf(cafe.getCafeNo()), menu_name);
        log.info("menu list" + cafeMenus);
        return cafeMenus;
    }

    @Transactional
    @Override
    public List<CafeMenu> sigList() {
        log.info("signature list");
        return repository.findBySignatureTrue();
    }

    

}
