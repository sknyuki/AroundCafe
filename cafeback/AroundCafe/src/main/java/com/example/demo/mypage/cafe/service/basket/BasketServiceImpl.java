package com.example.demo.mypage.cafe.service.basket;

import com.example.demo.mypage.cafe.dto.BasketDto;
import com.example.demo.mypage.cafe.entity.Basket;
import com.example.demo.mypage.cafe.repository.basket.BasketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BasketServiceImpl implements BasketService {

    @Autowired
    BasketRepository repository;


    @Transactional
    @Override
    public void includeImgSave(BasketDto info, String originalFilename) {
        String file = originalFilename; //info.getCafe_name()+"." +

        log.info("file: " +file);
        log.info("member no : " +info.getMem_no());

        Basket basket = new Basket(info.getShop_menu(),info.getShop_price(), info.getShop_cafe(), file, info.getShop_qun());
        repository.save(basket);

    }

    @Transactional
    @Override
    public void exceptImgSave(BasketDto info) {
        log.info("member no : " +info.getMem_no());


        Basket basket = new Basket(info.getShop_menu(),info.getShop_price(),info.getShop_cafe(), info.getShop_qun());
        repository.save(basket);
    }


    @Transactional
    @Override
    public List<Basket> list(Integer membNo) {
        List<Basket> findByMemNo = repository.findByMemNo(Long.valueOf(membNo));

        return findByMemNo;
    }

    @Transactional
    @Override
    public void delete(Integer ShopNo) throws IOException {
        Optional<Basket> findMenu = repository.findById(Long.valueOf(ShopNo));
        Basket basket = findMenu.get();

        if(basket.getMenu_img() != null) {
            Path file = Paths.get("../../cafefront/around_cafe/src/assets/basket/" + basket.getMenu_img());
            Files.delete(file);
        }

        repository.deleteById(Long.valueOf(ShopNo));
    }

}
