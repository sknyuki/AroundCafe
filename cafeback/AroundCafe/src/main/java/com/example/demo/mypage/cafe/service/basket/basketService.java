package com.example.demo.mypage.cafe.service.basket;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.CafeMenu;

import java.io.IOException;
import java.util.List;

public interface basketService {
    public void register(basket);

    public void exceptImgSave(CafeMenuDto info);

    public List<CafeMenu> list(Integer membNo);

    public void delete(Integer menuNo) throws IOException;

    List<CafeMenu> list1();
}
