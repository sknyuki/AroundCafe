package com.example.demo.review.service;

import com.example.demo.review.entity.Review;
import com.example.demo.review.entity.ReviewLike;
import com.example.demo.review.repository.ReviewLikeRepository;
import com.example.demo.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReviewLikeService {

    @Autowired
    ReviewLikeRepository reviewLikeRepository;

    @Autowired
    ReviewRepository repository;

    @Transactional
    public boolean addLike(Long reviewNo,String writer) {

        Optional<Review> review = Optional.ofNullable(repository.findByReviewNo(reviewNo).orElseGet(null));

        Review review1 =review.get();

        Review review2 =repository.findByReviewNo(reviewNo).orElseGet(()->null);

        if (review2 == null) {

            return false;
        }
        else{
            reviewLikeRepository.save(new ReviewLike(review2,writer));
        }

        return true;
    }
}




