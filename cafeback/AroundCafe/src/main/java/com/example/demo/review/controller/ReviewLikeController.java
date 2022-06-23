
package com.example.demo.review.controller;

import com.example.demo.review.entity.Review;
import com.example.demo.review.entity.ReviewLike;
import com.example.demo.review.service.ReviewLikeService;
import com.example.demo.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cafe/like")
@CrossOrigin(origins ="http://localhost:8080", allowedHeaders = "*")
public class ReviewLikeController {

    @Autowired
    private ReviewLikeService service;


    @PostMapping("/{reviewNo}/{membNo}")
    public boolean addHelp (@PathVariable("reviewNo") Long reviewNo,@PathVariable("membNo") Long membNo, @Validated @RequestBody ReviewLike reviewlike) {
        log.info ("review like " + reviewNo);
        return service.register(reviewNo,membNo,reviewlike);
    }

    @GetMapping("/{reviewNo}/{membNo}")
    public ReviewLike helpsRead(@PathVariable("reviewNo") Long reviewNo, @PathVariable("membNo") Long membNo) {
        log.info("helpsRead()");

        return service.helps(reviewNo, membNo);
    }
    @GetMapping("/list/{reviewNo}")
    public List<ReviewLike> helpsList(@PathVariable("reviewNo") Long reviewNo) {
        log.info("helpsList()");

        return service.list(reviewNo);
    }

    @GetMapping("/list/my/{membNo}")
    public List<Long> myHelpsList(@PathVariable("membNo") Long membNo) {

        log.info("myHelpsList(): " + membNo);
        //log.info(service.myLikes(membNo).toString());

        return service.myHelps(membNo);

    }


}





