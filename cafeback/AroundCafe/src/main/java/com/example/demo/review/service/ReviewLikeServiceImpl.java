
package com.example.demo.review.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.review.entity.Review;
import com.example.demo.review.entity.ReviewLike;
import com.example.demo.review.repository.ReviewLikeRepository;
import com.example.demo.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewLikeServiceImpl implements ReviewLikeService{

    @Autowired
    ReviewLikeRepository repository;

    @Autowired
    MemberRepository memberRepository;


    @Autowired
    ReviewRepository reviewRepository;


    @Override
    public boolean register(Long reviewNo, Long membNo, ReviewLike reviewlike) {
        Review review = reviewRepository.findById(reviewNo).orElseThrow();
        Member member = memberRepository.findById(membNo).orElseThrow();

        if(repository.findReviewLikeReviewNoAndMemberNo(reviewNo, membNo).isEmpty()) {
            reviewlike.setMember(member);
            reviewlike.setReview_info(review);
            repository.save(reviewlike);
            return true;

        } else {
            Optional<ReviewLike> maybeLikes = repository.findReviewLikeReviewNoAndMemberNo(reviewNo, membNo);
            repository.deleteById(maybeLikes.get().getHelpNo());
            return false;
        }
    }

    @Override
    public ReviewLike likes(Long reviewNo, Long membNo) {
        Optional<ReviewLike> maybeReadLikes = repository.findReviewLikeReviewNoAndMemberNo(reviewNo, membNo);
        if (maybeReadLikes.equals(Optional.empty())) {
            return null;
        }
        return maybeReadLikes.get();
    }


    @Override
    public List<ReviewLike> list(Long reviewNo) {

        return repository.findByReviewNo(reviewNo);
    }

    @Override
    public List<ReviewLike> myLikes(Long membNo) {
       // return repository.findByMemberNo(membNo);


        List<ReviewLike> rlList = repository.findByMemberNo(membNo);
        ReviewLike first = rlList.get(0);

        Member member = first.getMember();
        Long id = member.getMemNo();

        log.info("id: " + id);
        log.info("mem_id: " + member.getMemId());

        List<Review> rList = reviewRepository.findReviewByMemberNo(membNo);
        Review second = rList.get(0);
        log.info("id: " + id);
        log.info("mem_id: " + member.getMemId());


        Review review = first.getReview_info();
        Long review_num = review.getReviewNo();

        log.info("review_num: " + review_num);
        log.info("review_Like : " + review.getReviewLike());



        return rlList;


    }
}






