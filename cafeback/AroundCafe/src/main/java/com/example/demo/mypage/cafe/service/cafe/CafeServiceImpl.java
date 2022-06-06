package com.example.demo.mypage.cafe.service.cafe;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeImgTable;
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
    public void includeFileModifyCafe(Long cafeNo, String cafeImg) throws IOException {
        log.info("***service -> modify yes~ file info : "+ cafeNo);

        Optional<Cafe> cafe = repository.findById(cafeNo);
        Cafe cafe1 = cafe.get();
        CafeImgTable img = new CafeImgTable(cafeImg, cafe1);

        cafeImgRepository.save(img);
    }

    @Transactional
    @Override
    public void checkSavedImg(Long cafeNo) throws IOException {
        Optional<Integer> havingImg = cafeImgRepository.findByCafe_no(cafeNo);
        int check = havingImg.get();
        log.info("##service -> show cafeImg?" + check);

        if(check > 0) {
            List<CafeImgTable> findMyImg = cafeImgRepository.findCafe(cafeNo);

            for(int i = 0; i < findMyImg.size(); i++) {
                CafeImgTable checkImg = findMyImg.get(i);
                log.info("** saved img : " + checkImg.getCafe_img());
                Path filePath = Paths.get("../../cafefront/around_cafe/src/assets/cafe/cafeMypage/"+checkImg.getCafe_img());
                Files.delete(filePath);
            }
            cafeImgRepository.deleteByCafeNo(cafeNo);
        }
    }

    @Override
    public List<CafeImgTable> imgList(Integer cafeNo) {
        return cafeImgRepository.CafeImgList(Long.valueOf(cafeNo));
    }


    @Override
    public void notIncludeFileModifyCafe(Integer membNo,Cafe info) {

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
