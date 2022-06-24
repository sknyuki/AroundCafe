package com.example.demo.review.service;

import com.example.demo.review.entity.ReviewLike;
import java.util.List;

public interface  ReviewLikeService {

    public boolean register (Long reviewNo, Long membNo, ReviewLike reviewlike);
    ReviewLike helps (Long reviewNo, Long membNo);
    List<ReviewLike> list (Long reviewNo);
    List<Long> myHelps (Long membNo);
}




