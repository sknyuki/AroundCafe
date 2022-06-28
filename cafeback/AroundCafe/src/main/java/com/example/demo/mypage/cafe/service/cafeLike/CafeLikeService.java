package com.example.demo.mypage.cafe.service.cafeLike;

import com.example.demo.mypage.cafe.entity.CafeLike;
import com.example.demo.review.entity.ReviewLike;

import javax.transaction.Transactional;
import java.util.List;

public interface CafeLikeService {

    public boolean register (Long cafeNo, Long membNo, CafeLike cafeLike);
    CafeLike likesRead (Long cafeNo, Long membNo);
    List<CafeLike> list (Long cafeNo);
    List<Long> myLikes (Long membNo);
    List<CafeLike> searchIdList(Long searchId);


}
