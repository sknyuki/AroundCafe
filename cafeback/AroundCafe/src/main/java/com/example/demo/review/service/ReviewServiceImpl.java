package com.example.demo.review.service;


import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.review.dto.ReviewResponseDto;
import com.example.demo.review.entity.Review;
import com.example.demo.review.entity.ReviewLike;
import com.example.demo.review.repository.ReviewLikeRepository;
import com.example.demo.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository repository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CafeRepository cafeRepository;

    @Autowired
    ReviewLikeRepository reviewLikeRepository;

    @Transactional
    @Override
    public void register(Review review,@RequestParam(required = false) MultipartFile file, Integer membNo) throws Exception {
        Member member = memberRepository.findById(Long.valueOf(membNo)).orElseGet(null);

        if (file != null) {

            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" +file.getOriginalFilename();

            FileOutputStream saveFile = new FileOutputStream("../../cafefront/around_cafe/src/assets/review/" + fileName);

            saveFile.write(file.getBytes());
            saveFile.close();

            review.setFileName(fileName);
        }

        review.setMemberInfo(member);

        repository.save(review);

    }

    @Override
    public List<ReviewResponseDto> list(Integer cafeNo, Integer membNo) {
        log.info("review list service");

        List<Review> reviews = repository.findByCafeNo(Long.valueOf(cafeNo));
        List<ReviewResponseDto> response = new ArrayList<>();

        if(reviews.size() == 0){
            return null;
        }else {
            for (int i = 0; i < reviews.size(); i++) {
                List<ReviewLike> like = reviewLikeRepository.findByReview(reviews.get(i));

                Boolean checkHelps = false;
                if(like.size() == 0){
                    checkHelps = false;
                }else
                for(int j = 0; j < like.size() ; j ++) {
                    if(like.get(j).getMember().getMemNo() == Long.valueOf(membNo)){
                        checkHelps = true;
                        break;
                    }
                }

                ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
                        .reviewNo(reviews.get(i).getReviewNo())
                        .star_score(reviews.get(i).getStar_score())
                        .review_content(reviews.get(i).getReview_content())
                        .likeCnt(reviews.get(i).getLikeCnt())
                        .updDate(reviews.get(i).getUpdDate())
                        .memNick(reviews.get(i).getMemberInfo().getMemNick())
                        .memImg(reviews.get(i).getMemberInfo().getMemImg())
                        .fileName(reviews.get(i).getFileName())
                        .helps(checkHelps)
                        .build();

                response.add(reviewResponseDto);
            }

            return response;
        }
    }

    @Override
    public List<ReviewResponseDto> userList(Integer memNo) {
        Member member = memberRepository.findById(Long.valueOf(memNo)).orElseGet(null);
        List<Review> reviews = repository.findByMemberInfoOrderByReviewNoDesc(member);
        List<ReviewResponseDto> response = new ArrayList<>();

        if(reviews.size() == 0){
            return null;
        }else{

            for(int i = 0; i <reviews.size(); i++) {
                Cafe cafe = cafeRepository.findById(reviews.get(i).getCafeNum()).orElseGet(null);

                ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
                        .reviewNo(reviews.get(i).getReviewNo())
                        .star_score(reviews.get(i).getStar_score())
                        .review_content(reviews.get(i).getReview_content())
                        .likeCnt(reviews.get(i).getLikeCnt())
                        .updDate(reviews.get(i).getUpdDate())
                        .memNick(reviews.get(i).getMemberInfo().getMemNick())
                        .memImg(reviews.get(i).getMemberInfo().getMemImg())
                        .cafeNo(reviews.get(i).getCafeNum())
                        .cafeName(cafe.getCafe_name())
                        .fileName(reviews.get(i).getFileName())
                        .build();

                response.add(reviewResponseDto);
            }
            log.info("user list");
            return response;
        }

    }

    @Transactional
    @Override
    public Review read(Long reviewNo) {

        Optional<Review> maybeReadReview = repository.findById(Long.valueOf(reviewNo));
        if (maybeReadReview.equals(Optional.empty())) {
            log.info("Can't read review!");
            return null;

        } else {
            Review review = maybeReadReview.get();
            repository.save(review);
            return maybeReadReview.get();
        }
    }

    @Transactional
    @Override
    public void modify(Review review, @RequestParam(required = false) MultipartFile file) throws Exception {
        String fileName=null;
        if (review.getFileName() != null) {

            Path filePath = Paths.get("c:\\TeamProject\\AroundCafe\\cafefront\\around_cafe\\src\\assets\\review\\" + review.getFileName());
            Files.delete(filePath);
        }

        if (file != null) {

            UUID uuid = UUID.randomUUID();

            fileName =  uuid + "_" + file.getOriginalFilename();
            FileOutputStream saveFile = new FileOutputStream("../../cafefront/around_cafe/src/assets/review/" + fileName);

            saveFile.write(file.getBytes());
            saveFile.close();

            review.setFileName(fileName);
        }

        Review checkreview = repository.findById(review.getReviewNo()).orElseGet(null);
        Member member = memberRepository.findById(checkreview.getMemberInfo().getMemNo()).orElseGet(null);
        log.info("member info : " + member.getMemNo());
        checkreview.setStar_score(review.getStar_score());
        checkreview.setReview_content(review.getReview_content());
        checkreview.setFileName(fileName);
        checkreview.setMemberInfo(member);

        repository.save(checkreview);

    }

    @Transactional
    @Override
    public void delete(Integer reviewNo) throws IOException {
        Optional<Review> selectFile = repository.findById(Long.valueOf(reviewNo));
        Review deleteFile = selectFile.get();

        if ( deleteFile.getFileName() != null) {
            Path file = Paths.get("../../cafefront/around_cafe/src/assets/review/" + deleteFile.getFileName());

            Files.delete(file);
        }

        repository.deleteById(Long.valueOf(reviewNo));

    }

    @Transactional
    @Override
    public List<Review> myReviewList(Long memNo) {
        return repository.findReviewByMemberNo(memNo);
    }


//
//    @Override
//    public List<Review> CafeList(Long membNo) {
//        Cafe cafe = cafeRepository.findByCafeNo(Long.valueOf(membNo)).orElseGet(null);
//        Long cafeNo = cafe.getCafeNo();
//
//        log.info("cafe no : " + cafeNo);
//        return repository.findByCafeNum(cafeNo);
//    }
}


