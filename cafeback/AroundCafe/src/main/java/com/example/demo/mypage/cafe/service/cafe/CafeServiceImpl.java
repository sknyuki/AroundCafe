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
        log.info("*** is that check cafe table? ");
        Cafe cafe1 = cafe.get();
        log.info("*** yes! they have table content ");
        CafeImgTable img = new CafeImgTable(cafeImg, cafe1);

        cafeImgRepository.save(img);
        log.info("*** service -> img table's info :" +img);
        log.info("**!!img table is saved!!");
    }

    @Transactional
    @Override
    public void checkSavedImg(Long cafeNo) throws IOException {
        log.info("**service-> here is checking saved img, cafe no :" +cafeNo);
        Optional<Integer> havingImg = cafeImgRepository.findByCafe_no(cafeNo);
        int check = havingImg.get();
        log.info("##service -> show cafeImg?" + check);

        if(check > 0) {
            List<CafeImgTable> findMyImg = cafeImgRepository.findCafe(cafeNo);
            log.info("***service -> let's see saved img!!");

            for(int i = 0; i < findMyImg.size(); i++) {
                CafeImgTable checkImg = findMyImg.get(i);
                log.info("** saved img : " + checkImg.getCafe_img());
                Path filePath = Paths.get("../../cafefront/around_cafe/src/assets/cafe/cafeMypage/"+checkImg.getCafe_img());
                Files.delete(filePath);
            }
            cafeImgRepository.deleteByCafeNo(cafeNo);
            log.info("*** service -> delete before saved img!!!! ***");
        }
    }


    @Override
    public void notIncludeFileModifyCafe(Integer membNo,Cafe info) {
        log.info("** service : modify no file info!!!"+info);

        Optional<Member> findMemberNo = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMemberNo.get();

        info.setMemberInfo(member);

        repository.save(info);
        log.info("**!! cafe table is modified!!**");
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
