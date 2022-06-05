package com.example.demo.mypage.cafe.service.cafe;

import com.example.demo.mypage.cafe.entity.Cafe;

import java.io.IOException;

public interface CafeService {
    public void includeFileModifyCafe(Long cafeNo, String originalFilename) throws IOException;

    public void notIncludeFileModifyCafe(Integer cafeNo, Cafe info);

    //public Cafe cafeMypageread(Integer membNo);

    public Cafe read();

    public void checkSavedImg(Long cafeNo) throws IOException;
}
