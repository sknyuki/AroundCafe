package com.example.demo.review.controller;

import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.review.entity.Review;
import com.example.demo.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cafe/review")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @Autowired
    private CafeRepository cafeRepository;


    //리뷰 등록
    @PostMapping("/register/{membNo}")
    public void reviewRegister (@Validated Review review, @RequestParam(required = false) MultipartFile file,
                                @PathVariable("membNo") Integer membNo) throws Exception {
        log.info("reviewRegister()" + review + "file" + file);

        service.register(review, file,membNo);
    }


    //리뷰 목록
    @GetMapping("/list")
    public List<Review> reviewList () {
        log.info("ReviewList()");

        return service.list ();
    }

    //카페에서 리뷰 리스트 조회
    @GetMapping("/list/{membNo}")
    public List<Review> cafeReviewList (@PathVariable("membNo") Integer membNo) {
        log.info("cafe ReviewList()");
        log.info("member no : " + membNo);
        Cafe cafe = cafeRepository.findByCafeNo(Long.valueOf(membNo)).orElseGet(null);

        if(cafe == null) {
            return null;
        }else {
            log.info("cafe no :" +cafe.getCafeNo());
            return service.CafeList(cafe.getCafeNo());
        }

    }

    //리뷰 읽기
    @GetMapping("/{reviewNo}")
    public Review reviewRead (
            @PathVariable("reviewNo") Long reviewNo) {
        log.info("reviewRead()");
        return service.read(reviewNo);
    }

    //리뷰 수정
    @PutMapping("/{reviewNo}")
    public Review reviewModify (
            @PathVariable("reviewNo") Integer reviewNo,
            Review review, @RequestParam(required = false) MultipartFile file) throws Exception {
        log.info("reviewModify(): " + review + "reviewNo" + reviewNo);

        review.setReviewNo(Long.valueOf(reviewNo));
        service.modify(review, file);

        return review;
    }


    //리뷰 삭제
    @DeleteMapping("/{reviewNo}")
    public void reviewRemove(
            @PathVariable("reviewNo") Integer reviewNo) throws IOException {
        log.info("reviewRemove()");

        service.delete(reviewNo);
    }

    //리뷰 리스트에서 도움이 됐어요 할 때
    /*
    @PostMapping("/{reviewNo}/help")
    public boolean addHelp ( @PathVariable("reviewNo") Long reviewNo ) {
        log.info ("review help() " +  + reviewNo);
        return service.addHelp(reviewNo);
    }

     */


    //마이페이지에서 내가 쓴 게시판 글 불러오기
    /*
    @GetMapping("/list/{searchId}")
    public List<Review> reviewPerList (@PathVariable("searchId") String searchId) {
        log.info("reviewPerList()");


        return service.searchIdList(searchId);
    }

     */
}
