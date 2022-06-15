
package com.example.demo.review.controller;

import com.example.demo.review.entity.Review;
import com.example.demo.review.service.ReviewLikeService;
import com.example.demo.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cafe/review")
@CrossOrigin(origins ="http://localhost:8080", allowedHeaders = "*")
public class ReviewLikeController {

    @Autowired
    private ReviewLikeService service;

    @PostMapping("/{reviewNo}/like")
    public boolean addLike ( @PathVariable("reviewNo") Long reviewNo,String writer ) {
        log.info ("review like " + reviewNo);
        return service.addLike(reviewNo,writer);
    }


}





