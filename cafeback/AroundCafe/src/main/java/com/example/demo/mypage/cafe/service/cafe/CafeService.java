package com.example.demo.mypage.cafe.service.cafe;

import com.example.demo.mypage.cafe.entity.Cafe;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CafeService {
    public void includeFileModifyCafe(Integer cafeNo, Cafe info, List<MultipartFile> fileList) throws IOException;

    public void notIncludeFileModifyCafe(Integer cafeNo, Cafe info);

    public Cafe cafeMypageread(Integer membNo);
}
