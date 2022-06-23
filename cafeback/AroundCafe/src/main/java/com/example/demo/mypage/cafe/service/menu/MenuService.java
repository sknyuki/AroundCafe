package com.example.demo.mypage.cafe.service.menu;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.CafeMenu;

import java.io.IOException;
import java.util.List;

public interface MenuService {
    public void includeImgSave(CafeMenuDto info, String fileName);

    public void exceptImgSave(CafeMenuDto info);

    public void includeImgModify(CafeMenu info, String originalFilename, Integer cafeNo) throws IOException;

    public void exceptImgModify(CafeMenu info, Integer cafeNo);

    public List<CafeMenu> list(Integer membNo);

    public void delete(Integer menuNo) throws IOException;

    public List<CafeMenu> list1();

    public String changeSignature(Integer menuNo);

    public List<CafeMenu> sigList();

    public String changeSoldOut(Integer menuNo);

    public String deleteSoldOut(Integer menuNo);

    public List<CafeMenu> soldList();

    public List<CafeMenu> findMenu(Integer cafe_no, String cafe_name);
}
