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
    public void includeFileModifyCafe(Integer membNo, Cafe info, List<MultipartFile> fileList) throws IOException {
        //1~2번 정리 완료
        Optional<Member> findMemberNo = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMemberNo.get();

        Cafe cafe = new Cafe(info.getCafe_name(), info.getCafe_bis_no(), info.getCafe_time(), info.getCafe_content(), info.getCafe_call(), info.getCafe_adr1(),info.getCafe_adr2(),info.getCafe_adr3(),member);
        repository.save(cafe);

        deleteAndSaveImg(info, fileList, cafe);

        /*
        1. id정보로 member db에서 member_no 가지고 오기.(중복여부를 하기 때문에 문제없음)
        2. cafe db에 정보 + 조인 member 정보 넣기 -> db저장
        3. 위에 정의한 이미지를 이미지 db에 이미지 이름 + 조인 cafe 정보 넣기 -> db저장 끝!
        아래 파일 없는 것은 1,2번만 적용하면 됨.

        변경사항 -> 태호씨가 회원가입 시 카페 db에 이름, 사업자번호 입력하기로 함.
        -> 마이페이지에서 업로드 하는 것은 이제 modify형식으로 변경 될 예정이다.
         */
    }

    public void deleteAndSaveImg(Cafe info, List<MultipartFile> fileList, Cafe cafe) throws IOException {
        //이미지에 대한 이름 재 정의
        List findImg = new ArrayList<>();
        for(MultipartFile multipartFile : fileList) {
            String cafeImg = info.getCafe_name()+"."+multipartFile.getOriginalFilename();
            findImg.add(cafeImg);
            log.info("fileName : "+cafeImg);
        }

        //카페이미지에서 카페 관련해서 이미지가 있나 찾아보기 -> 있으면 삭제 후 저장, 없으면 그냥 저장
        Optional<CafeImg> havingImg = cafeImgRepository.findByCafe_no(Math.toIntExact(info.getCafeNo()));
        CafeImg havingImg2 = havingImg.get();

        if(havingImg2.getCafe_img() != null) {
            List<String> findMyImg = cafeImgRepository.findByCafe_img(Math.toIntExact(info.getCafeNo()));
            List<String> deleteImg = new ArrayList<>();
            //파일 찾기..
            for(int i = 0; i < findMyImg.size(); i++) {
                log.info("img : " + findMyImg.get(i));
                deleteImg.add(findMyImg.get(i));
            }
            //파일 삭제..
            for(int i = 0; i < deleteImg.size(); i++) {
                Path filePath = Paths.get("../../cafefront/around_cafe/src/asserts/cafeMypage"+findMyImg.get(i));
                Files.delete(filePath);
            }
        }

        //카페 이미지 테이블에 list로 만들어놓은 이미지 이름들을 하나씩 저장하기
        for(int i = 0; i < findImg.size(); i++) {
            String img = (String) findImg.get(i);
            CafeImg cafeImg = new CafeImg(img,cafe);
            cafeImgRepository.save(cafeImg);
        }

    }

    @Override
    public void notIncludeFileModifyCafe(Integer membNo,Cafe info) {
        Optional<Member> findMemberNo = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMemberNo.get();

        Optional<Cafe> findCafe = repository.findByMemberNo(membNo);
        Cafe findCafe1 = findCafe.get();

        Cafe cafe = new Cafe(info.getCafe_name(), findCafe1.getCafe_bis_no(), info.getCafe_time(), info.getCafe_content(), info.getCafe_call(), info.getCafe_adr1(),info.getCafe_adr2(),info.getCafe_adr3(),member);
        repository.save(cafe);
    }

    @Override
    public Cafe cafeMypageread(Integer membNo) {
        Optional<Cafe> findCafe = repository.findByMemberNo(membNo);
        Cafe cafe = findCafe.get();

        return cafe;
    }
}
