package com.example.demo.mypage.cafe.service.basket;

import com.example.demo.mypage.cafe.dto.BasketDto;
import com.example.demo.mypage.cafe.entity.Basket;

import java.io.IOException;
import java.util.List;

public interface BasketService {
    public void includeImgSave(BasketDto info, String originalFilename);

    public void exceptImgSave(BasketDto info);

    public List<Basket> list(Integer membNo);

    public void delete(Integer ShopNo) throws IOException;

}
