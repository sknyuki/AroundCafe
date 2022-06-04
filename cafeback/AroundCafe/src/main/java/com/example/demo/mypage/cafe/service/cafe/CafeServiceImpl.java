package com.example.demo.mypage.cafe.service.cafe;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeImg;
import com.example.demo.mypage.cafe.repository.cafe.CafeImgRepository;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    @Transactional
    @Override
    public void includeFileModifyCafe(Integer membNo, Cafe info, String originalFilename) throws IOException {
        log.info("***modify yes~ file info : "+info);
        //1~2번 정리 완료
        Optional<Member> findMemberNo = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMemberNo.get();

        info.setMemberInfo(member);
        log.info(member.toString());
        repository.save(info);
        log.info(info.toString());

        //이미지 db에서 파일 삭제, 추가 저장 메소드
        CafeImg cafeImg = new CafeImg(originalFilename, info);
        log.info(cafeImg.toString());

        cafeImgRepository.save(cafeImg);
        log.info(cafeImg.toString());


        /*
        1. id정보로 member db에서 member_no 가지고 오기.(중복여부를 하기 때문에 문제없음)
        2. cafe db에 정보 + 조인 member 정보 넣기 -> db저장
        3. 위에 정의한 이미지를 이미지 db에 이미지 이름 + 조인 cafe 정보 넣기 -> db저장 끝!
        아래 파일 없는 것은 1,2번만 적용하면 됨.

        변경사항 -> 태호씨가 회원가입 시 카페 db에 이름, 사업자번호 입력하기로 함.
        -> 마이페이지에서 업로드 하는 것은 이제 modify형식으로 변경 될 예정이다.
         */
    }

//    public void deleteAndSaveImg(Cafe info, String originalFilename) throws IOException {
//        log.info("*****here is deleteAnd Saving Img****");
//        //카페이미지에서 카페 관련해서 이미지가 있나 찾아보기 -> 있으면 삭제 후 저장, 없으면 그냥 저장
//        Optional<CafeImg> havingImg = cafeImgRepository.findByCafeNo(info.getCafeNo());
//
//        if(havingImg != null) {
//            List<String> findMyImg = cafeImgRepository.findByCafeImg(info.getCafeNo());
//
//            for(int i = 0; i < findMyImg.size(); i++) {
//                log.info("img : " + findMyImg.get(i));
//                Path filePath = Paths.get("../../cafefront/around_cafe/src/asserts/cafe/cafeMypage"+findMyImg.get(i));
//                Files.delete(filePath);
//                cafeImgRepository.deleteByCafeNo(info.getCafeNo());
//            }
//        }
//
//        for(int i =0; i < findImg.size(); i++) {
//            log.info("save cafe_img : " + (String) findImg.get(i));
//            CafeImg cafeImg = new CafeImg((String) findImg.get(i), info);
//            cafeImgRepository.save(cafeImg);
//        }
//    }

    @Override
    public void notIncludeFileModifyCafe(Integer membNo,Cafe info) {
        log.info("modify no file info!!!"+info);

        Optional<Member> findMemberNo = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMemberNo.get();

        info.setMemberInfo(member);

        repository.save(info);
    }

//    @Override
//    public Cafe cafeMypageread(Integer membNo) {
//        Optional<Cafe> findCafe = repository.findByMemberNo(Long.valueOf(membNo));
//        Cafe cafe = findCafe.get();
//
//        return cafe;
//    }

    @Override
    public Cafe read() {
        Long memNo = 1l;
        Optional<Cafe> findCafe = repository.findById(memNo);
        Cafe cafe = findCafe.get();

        return cafe;
    }
}
