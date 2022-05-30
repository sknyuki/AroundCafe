package com.example.demo.mypage.cafe.service;

import com.example.demo.mypage.cafe.dto.CafeDto;
import com.example.demo.mypage.cafe.entity.Cafe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CafeService {
    void includeFileRegisterCafe(Integer cafeNo, Cafe info, List<MultipartFile> fileList);

    void notIncludeFileRegisterCafe(Integer cafeNo, Cafe info);
}
