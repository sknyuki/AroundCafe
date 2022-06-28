package com.example.demo.mypage.cafe.service.cafeLike;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeLike;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.mypage.cafe.repository.cafeLike.CafeLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CafeLikeServiceImpl implements CafeLikeService{

    @Autowired
    CafeLikeRepository repository;

    @Autowired
    MemberRepository memberRepository;


    @Autowired
    CafeRepository cafeRepository;

    @Override
    public boolean register(Long cafeNo, Long membNo, CafeLike cafeLike) {
        Cafe cafe = cafeRepository.findById(cafeNo).orElseThrow();
        Member member = memberRepository.findById(membNo).orElseThrow();

        if(repository.findCafeLikeCafeNoAndCafeNo(cafeNo, membNo).isEmpty()) {
            cafeLike.setMember(member);
            cafeLike.setCafe(cafe);
            repository.save(cafeLike);
            return true;

        } else {
            Optional<CafeLike> maybeLikes1 = repository.findCafeLikeCafeNoAndCafeNo(cafeNo, membNo);
            repository.deleteById(maybeLikes1.get().getLikeNo());
            return false;
        }
    }

    @Override
    public CafeLike likesRead(Long cafeNo, Long membNo) {
        Optional<CafeLike> maybeReadLikes1 = repository.findCafeLikeCafeNoAndCafeNo(cafeNo, membNo);
        if (maybeReadLikes1.equals(Optional.empty())) {
            return null;
        }
        return maybeReadLikes1.get();
    }

    @Override
    public List<CafeLike> list(Long cafeNo) {
        return repository.findByCafeNo(cafeNo);
    }

    @Override
    public List<Long> myLikes(Long membNo) {
        List<Long> reviewList = new ArrayList<>();

        List<CafeLike> rlList = repository.findByMemberNo(membNo);
        for(CafeLike cafeLike :rlList){
            reviewList.add(cafeLike.getCafe().getCafeNo());
        }
        return reviewList;
    }

    @Transactional
    @Override
    public List<CafeLike> searchIdList(Long searchId) {
        return repository.findIdList(String.valueOf(searchId));

    }
}

