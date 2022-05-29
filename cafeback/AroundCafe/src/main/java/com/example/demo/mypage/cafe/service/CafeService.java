package com.example.demo.mypage.cafe.service;

import com.example.demo.mypage.cafe.dto.CafeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CafeService {
    void includeFileRegisterCafe(CafeDto info, List<MultipartFile> fileList);

    void notIncludeFileRegisterCafe(CafeDto info);
}
