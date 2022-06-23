package com.example.demo.review.service;

import com.example.demo.review.entity.ReviewLike;
import java.util.List;

public interface  ReviewLikeService {

    public boolean register (Long reviewNo, Long membNo, ReviewLike reviewlike);
    ReviewLike likes (Long reviewNo, Long membNo);
    List<ReviewLike> list (Long reviewNo);
    List<ReviewLike> myLikes (Long membNo);
}




