package com.example.demo.mypage.cafe.service;

import com.example.demo.mypage.cafe.dto.CafeDto;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeImg;
import com.example.demo.mypage.cafe.repository.CafeImgRepository;
import com.example.demo.mypage.cafe.repository.CafeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CafeImgRepository cafeImgRepository;


    @Override
    public void includeFileRegisterCafe(CafeDto info, List<MultipartFile> fileList) {
        //이미지에 대한 이름 재 정의
        List findImg = new ArrayList<>();
        for(MultipartFile multipartFile : fileList) {
            String cafeImg = info.getId()+"."+multipartFile.getOriginalFilename();
            findImg.add(cafeImg);
            log.info("fileName : "+cafeImg);
        }
        //1~2번 정리 완료
        Origin<Member>  findMemberNo = memberRepository.findMemberId(info.getId());
        Member member = findMemberNo.get();

        Cafe cafe = new Cafe(info.getCafe_name(), info.getCafe_time(), info.getCafe_content(), info.getCafe_call(), info.getCafe_adr1(),info.getCafe_adr2(),info.getCafe_adr3(),member);
        repository.save(cafe);

        //방금 저장한 cafe의 정보 가지고 오기, 이걸 안해도 문제가 없나?(desc로 조회하면 마지막에 저장한게 확인될 것임)
        Optional<Cafe> findCafe = repository.findOne();
        Cafe cafe1 = findCafe.get();

        //카페 이미지 테이블에 list로 만들어놓은 이미지 이름들을 하나씩 저장하기
        for(int i = 0; i < findImg.size(); i++) {
            String img = (String) findImg.get(i);
            CafeImg cafeImg = new CafeImg(img,cafe1);
            cafeImgRepository.save(cafeImg);
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
        Origin<Member>  findMemberNo = memberRepository.findMemberId(info.getId());
        Member member = findMemberNo.get();

        Cafe cafe = new Cafe(info.getCafe_name(), info.getCafe_time(), info.getCafe_content(), info.getCafe_call(), info.getCafe_adr1(),info.getCafe_adr2(),info.getCafe_adr3(),member);
        repository.save(cafe);
    }
}
