package com.example.demo.mypage.cafe.service.cafe;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.dto.CafeStarAverResponse;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeImgTable;
import com.example.demo.mypage.cafe.map.CafeResponseMapSturct;
import com.example.demo.mypage.cafe.repository.cafe.CafeImgRepository;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.mypage.cafe.repository.menu.MenuRepository;
import com.example.demo.review.entity.Review;
import com.example.demo.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CafeServiceImpl implements CafeService {
    @Autowired
    CafeRepository repository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CafeImgRepository cafeImgRepository;

    @Autowired
    MenuRepository menuRepository;

    @Transactional
    @Override
    public void notIncludeFileModifyCafe(Integer membNo, Cafe info) {
        Member member = memberRepository.findById(Long.valueOf(membNo)).orElseGet(null);
        info.setMemberInfo(member);

        repository.save(info);
    }

    @Transactional
    @Override
    public void includeFileModifyCafe(Long cafeNo, String cafeImg) throws IOException {
        log.info("***service -> modify yes~ file info : " + cafeNo);
        Cafe cafe = repository.findById(Long.valueOf(cafeNo)).orElseGet(null);
        CafeImgTable img = CafeImgTable.builder()
                .cafe_img(cafeImg)
                .cafe(cafe)
                .build();

        cafeImgRepository.save(img);
    }

    @Transactional
    @Override
    public void checkSavedImg(Long cafeNo) throws IOException {
        Integer checkImgCount = cafeImgRepository.findByCafe_no(cafeNo).orElseGet(null);
        log.info("##service -> show cafeImg?" + checkImgCount);

        if (checkImgCount > 0) {
            List<CafeImgTable> findMyImg = cafeImgRepository.findCafe(cafeNo);

            for (int i = 0; i < findMyImg.size(); i++) {
                CafeImgTable checkImg = findMyImg.get(i);
                log.info("** saved img : " + checkImg.getCafe_img());
                Path filePath = Paths.get("../../cafefront/around_cafe/src/assets/cafe/cafeMypage/" + checkImg.getCafe_img());
                Files.delete(filePath);
            }
            cafeImgRepository.deleteByCafeNo(cafeNo);
        }
    }

    @Transactional
    @Override
    public List<CafeImgTable> imgList(Integer memNo) {
        Cafe cafe = repository.findByMemberNo(Long.valueOf(memNo)).orElseGet(null);
        return cafeImgRepository.CafeImgList(cafe.getCafeNo());
    }

    @Transactional
    @Override
    public List<Cafe> cafeList() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void delete(String cafeNo) throws IOException {
        checkSavedImg(Long.valueOf(cafeNo)); //카페 이미지 테이블에 있는 이미지 삭제 + 테이블 삭제
        log.info("cafe img : " + cafeNo + "is deleted!!");

        repository.deleteById(Long.valueOf(cafeNo));
        log.info("cafe table is deleted!" + cafeNo);
    }


//    @Override
//    public Cafe cafeMypageread(Integer membNo) {
//        Optional<Cafe> findCafe = repository.findByMemberNo(Long.valueOf(membNo));
//        Cafe cafe = findCafe.get();
//
//        return cafe;
//    }

    @Transactional
    @Override
    public Cafe read(Integer cafeNo) {
        Cafe cafe = repository.findById(Long.valueOf(cafeNo)).orElseGet(null);
        log.info("cafe info" + cafe);

        return cafe;
    }

    // 태호씨에게 질문
    @Transactional
    @Override
    public List<CafeStarAverResponse> list() {
        List<Cafe> cafeList = repository.findAll();
        List<CafeStarAverResponse> cafeStarAverResponses = new ArrayList<>();

        for (Cafe cafe : cafeList) {
            CafeStarAverResponse cafeStarAverResponse = CafeResponseMapSturct.instance.toDto(cafe);
            List<Review> reviewList = reviewRepository.findAllReviewByCafeNo(cafe.getCafeNo());


            int starScoreTemp = 0;
            int count = 0;
            for (Review review : reviewList) {
                starScoreTemp += review.getStar_score();//총합
                count += 1;//인원수

            }
            log.info("reviewRepository:"+reviewRepository.findAllReviewByCafeNo(cafe.getCafeNo()));
            log.info("reviewList.length: "+reviewList.size());
            log.info("total star: "+starScoreTemp);
            log.info("count: "+count);

            if(starScoreTemp==0){
                double starAverage=0;
                cafeStarAverResponse.setStarAver(starAverage);
            }else {
            double starAverage = starScoreTemp / count;//평균값 계산
                cafeStarAverResponse.setStarAver(starAverage);}


            cafeStarAverResponses.add(cafeStarAverResponse);
        }
        return cafeStarAverResponses;
    }
}
