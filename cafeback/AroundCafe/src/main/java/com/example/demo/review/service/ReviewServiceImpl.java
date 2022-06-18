package com.example.demo.review.service;


import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.review.entity.Review;
import com.example.demo.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
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

    @Transactional
    @Override
    public void register(Review review,@RequestParam(required = false) MultipartFile file) throws Exception {

        if (file != null) {


            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" +file.getOriginalFilename();

            FileOutputStream saveFile = new FileOutputStream("../../cafefront/around_cafe/src/assets/review/" + fileName);

            saveFile.write(file.getBytes());
            saveFile.close();

            review.setFileName(fileName);
        }

        repository.save(review);

    }

    @Transactional
    @Override
    public List<Review> list() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "cafeNum"));
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


        Review review1 = repository.findById(review.getReviewNo()).orElseGet(null);


        if (review1.getFileName() != null) {

            Path filePath = Paths.get("c:\\TeamProject\\AroundCafe\\cafefront\\around_cafe\\src\\assets\\review\\" + review1.getFileName());
            Files.delete(filePath);
            log.info("file delete complete");
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
    /*
    @Transactional
    @Override
    public void modify(Review review, @RequestParam(required = false) MultipartFile file) throws Exception {

        Review review3 = repository.findById(review.getReviewNo()).orElseGet(()->null);

        if (review3.getFileName().equals(Optional.empty())) {

            Path filePath = Paths.get("c:\\TeamProject\\AroundCafe\\cafefront\\around_cafe\\src\\assets\\review\\" + review.getFileName());
            Files.delete(filePath);
            log.info("file delete complete");
        }

        if (file != null) {

            UUID uuid = UUID.randomUUID();

            String fileName =  uuid + "_" + file.getOriginalFilename();
            FileOutputStream saveFile = new FileOutputStream("../../cafefront/around_cafe/src/assets/review/" + fileName);

            saveFile.write(file.getBytes());
            saveFile.close();

            review3.setFileName(fileName);
        }

        repository.save(new Review());

    }

 */

    @Transactional
    @Override
    public void delete(Integer reviewNo) throws IOException {
        Optional<Review> selectFile = repository.findById(Long.valueOf(reviewNo));
        log.info("check1");
        Review deleteFile = selectFile.get();
        log.info("check2");

        if ( deleteFile.getFileName() != null) {
            log.info("check3");
            Path file = Paths.get("../../cafefront/around_cafe/src/assets/review/" + deleteFile.getFileName());

            Files.delete(file);
            log.info("check4");
        }
        log.info("check5");
        repository.deleteById(Long.valueOf(reviewNo));

    }

    @Transactional
    @Override
    public List<Review> myReviewList(Long memNo) {
        return repository.findReviewByMemberNo(memNo);
    }


}


