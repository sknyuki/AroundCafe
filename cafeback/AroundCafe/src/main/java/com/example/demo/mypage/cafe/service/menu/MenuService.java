package com.example.demo.mypage.cafe.service.menu;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.CafeMenu;

import java.io.IOException;
import java.util.List;

public interface MenuService {
    public void includeImgSave(CafeMenuDto info, String originalFilename);

    public void exceptImgSave(CafeMenuDto info);

    public void includeImgModify(CafeMenu info, String originalFilename, String cafeName) throws IOException;

    public void exceptImgModify(CafeMenu info);

    public List<CafeMenu> list(Integer membNo);

    public void delete(Integer menuNo) throws IOException;

    public List<CafeMenu> list1();
}
