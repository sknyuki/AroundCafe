package com.example.demo.mypage.cafe.service.cafe;

import com.example.demo.mypage.cafe.dto.CafeStarAverResponse;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeImgTable;

import java.io.IOException;
import java.util.List;

public interface CafeService {
    public void includeFileModifyCafe(Long cafeNo, String originalFilename) throws IOException;

    public void notIncludeFileModifyCafe(Integer cafeNo, Cafe info);

    //public Cafe cafeMypageread(Integer membNo);

    public Cafe read(Integer membNo);

    public void checkSavedImg(Long cafeNo) throws IOException;

    public List<CafeImgTable> imgList(Integer memNo);

    public List<Cafe> cafeList();

    public void delete(String cafeNo) throws IOException;

    public List<CafeStarAverResponse> list();

    public String img(Integer cafeNo);
}
