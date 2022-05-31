package com.example.demo.mypage.cafe.service.menu;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;

public interface MenuService {
    public void includeImgSave(CafeMenuDto info, String originalFilename);

    public void exceptImgSave(CafeMenuDto info);
}
