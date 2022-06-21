package com.example.demo.review.service;

import com.example.demo.review.entity.Review;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public interface ReviewService {

    public void register (Review review, @RequestParam(required = false) MultipartFile file, Integer membNo) throws Exception;
    public List<Review> list (Integer membNo);
    public Review read (Long reviewNo);
    public void modify (Review review, @RequestParam(required = false) MultipartFile file) throws Exception;
    public void delete (Integer reviewNo) throws IOException;
    public List<Review> myReviewList(Long memNo); //멤버 번호로 내 리뷰 찾기
    //public List<Review> CafeList(Long cafeNo);
}
