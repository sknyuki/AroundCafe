/*
package com.example.demo.review.service;

import com.example.demo.review.entity.Review;
import com.example.demo.review.entity.ReviewLike;
import com.example.demo.review.repository.ReviewLikeRepository;
import com.example.demo.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service

public class ReviewLikeServiceImpl implements ReviewLikeService{

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    @Autowired
    private ReviewRepository repository;


    @Transactional
    @Override
    public boolean addLike(String writer,Long reviewNo) {

        Review review = repository.findByReviewNo(reviewNo).orElseThrow();

        if (reviewLikeRepository.findByReview(review).isEmpty()) {

            reviewLikeRepository.save(new ReviewLike());

            return true;
        }

        return false;
    }
}

 */




