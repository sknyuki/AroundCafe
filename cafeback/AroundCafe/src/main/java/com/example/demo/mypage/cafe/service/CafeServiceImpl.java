package com.example.demo.mypage.cafe.service;

import com.example.demo.mypage.cafe.dto.CafeDto;
import com.example.demo.mypage.cafe.repository.CafeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CafeServiceImpl implements CafeService{
    @Autowired
    CafeRepository repository;


    @Override
    public void includeFileRegisterCafe(CafeDto info, List<MultipartFile> fileList) {
        //이미지에 대한 이름 재 정의
        List findImg = new ArrayList<>();
        for(MultipartFile multipartFile : fileList) {
            String cafeImg = info.getId()+"."+multipartFile.getOriginalFilename();
            findImg.add(cafeImg);
        }

        /*
        1. id정보로 member db에서 member_no 가지고 오기.(중복여부를 하기 때문에 문제없음)
        2. cafe db에 정보 + 조인 member 정보 넣기 -> db저장
        3. 위에 정의한 이미지를 이미지 db에 이미지 이름 + 조인 cafe 정보 넣기 -> db저장 끝!
        아래 파일 없는 것은 1,2번만 적용하면 됨.
         */
    }

    @Override
    public void notIncludeFileRegisterCafe(CafeDto info) {

    }
}
