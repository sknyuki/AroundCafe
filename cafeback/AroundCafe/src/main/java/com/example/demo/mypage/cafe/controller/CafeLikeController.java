package com.example.demo.mypage.cafe.controller;

import com.example.demo.mypage.cafe.entity.CafeLike;
import com.example.demo.mypage.cafe.service.cafeLike.CafeLikeService;
import com.example.demo.review.entity.ReviewLike;
import com.example.demo.review.service.ReviewLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cafe/likes")
@CrossOrigin(origins ="http://localhost:8080", allowedHeaders = "*")
public class CafeLikeController {

    @Autowired
    private CafeLikeService service;


    @PostMapping("/{cafeNo}/{membNo}")
    public boolean addLikes (@PathVariable("cafeNo") Long cafeNo, @PathVariable("membNo") Long membNo, @Validated @RequestBody CafeLike cafeLike) {
        log.info ("cafe like " + cafeNo);
        return service.register(cafeNo,membNo,cafeLike);
    }

    @GetMapping("/{cafeNo}/{membNo}")
    public CafeLike likesRead(@PathVariable("cafeNo") Long cafeNo, @PathVariable("membNo") Long membNo) {
        log.info("likesRead()");

        return service.likesRead(cafeNo, membNo);
    }

    @GetMapping("/list/{cafeNo}")
    public List<CafeLike> likesList(@PathVariable("cafeNo") Long cafeNo) {
        log.info("likesList()");

        return service.list(cafeNo);
    }

    @GetMapping("/list/my/{membNo}")
    public List<Long> myHelpsList(@PathVariable("membNo") Long membNo) {

        log.info("myHelpsList(): " + membNo);

        return service.myLikes(membNo);

    }

}
