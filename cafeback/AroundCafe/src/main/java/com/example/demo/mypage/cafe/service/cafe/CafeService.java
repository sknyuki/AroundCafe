package com.example.demo.mypage.cafe.service.cafe;

import com.example.demo.mypage.cafe.entity.Cafe;

import java.io.IOException;

public interface CafeService {
    public void includeFileModifyCafe(Integer cafeNo, Cafe info, String originalFilename) throws IOException;

    public void notIncludeFileModifyCafe(Integer cafeNo, Cafe info);

    //public Cafe cafeMypageread(Integer membNo);

    public Cafe read();
}
