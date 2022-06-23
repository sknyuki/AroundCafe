package com.example.demo.review.service;


import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.review.entity.Review;
import com.example.demo.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository repository;


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CafeRepository cafeRepository;

    @Transactional
    @Override
    public void register(Review review,@RequestParam(required = false) MultipartFile file, Integer membNo) throws Exception {
        Member member = memberRepository.findById(Long.valueOf(membNo)).orElseGet(null);

        if (file != null) {

            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" +file.getOriginalFilename();

            FileOutputStream saveFile = new FileOutputStream("../../cafefront/around_cafe/src/assets/review/" + fileName);

            saveFile.write(file.getBytes());
            saveFile.close();

            review.setFileName(fileName);
        }

        review.setMemberInfo(member);

        repository.save(review);

    }

    @Override
    public List<Review> list(Integer cafeNo) {

        log.info("cafe no : " + cafeNo);
        List<Review> reviews = repository.findByCafeNo(Long.valueOf(cafeNo));
        return reviews;
    }

    @Transactional
    @Override
    public Review read(Long reviewNo) {

        Optional<Review> maybeReadReview = repository.findById(Long.valueOf(reviewNo));
        if (maybeReadReview.equals(Optional.empty())) {
            log.info("Can't read review!");
            return null;

        } else {
            Review review = maybeReadReview.get();
            repository.save(review);
            return maybeReadReview.get();
        }
    }

    @Transactional
    @Override
    public void modify(Review review, @RequestParam(required = false) MultipartFile file) throws Exception {

        if (review.getFileName() != null) {

            Path filePath = Paths.get("c:\\TeamProject\\AroundCafe\\cafefront\\around_cafe\\src\\assets\\review\\" + review.getFileName());
            Files.delete(filePath);
        }

        if (file != null) {

            UUID uuid = UUID.randomUUID();

            String fileName =  uuid + "_" + file.getOriginalFilename();
            FileOutputStream saveFile = new FileOutputStream("../../cafefront/around_cafe/src/assets/review/" + fileName);

            saveFile.write(file.getBytes());
            saveFile.close();

            review.setFileName(fileName);
        }

        repository.save(review);

    }

    @Transactional
    @Override
    public void delete(Integer reviewNo) throws IOException {
        Optional<Review> selectFile = repository.findById(Long.valueOf(reviewNo));
        Review deleteFile = selectFile.get();

        if ( deleteFile.getFileName() != null) {
            Path file = Paths.get("../../cafefront/around_cafe/src/assets/review/" + deleteFile.getFileName());

            Files.delete(file);
        }

        repository.deleteById(Long.valueOf(reviewNo));

    }

    @Transactional
    @Override
    public List<Review> myReviewList(Long memNo) {
        return repository.findReviewByMemberNo(memNo);
    }
//
//    @Override
//    public List<Review> CafeList(Long membNo) {
//        Cafe cafe = cafeRepository.findByCafeNo(Long.valueOf(membNo)).orElseGet(null);
//        Long cafeNo = cafe.getCafeNo();
//
//        log.info("cafe no : " + cafeNo);
//        return repository.findByCafeNum(cafeNo);
//    }
}


