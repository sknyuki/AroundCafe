package com.example.demo.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class ReviewResponseDto {
    private Long reviewNo;
    private Integer star_score;
    private String review_content;
    private String fileName;
    private int likeCnt;
    private String updDate;
    private String memNick;
    private String memImg;
    private Long cafeNo;
    private String cafeName;
    private Boolean helps;


    @Builder
    public ReviewResponseDto(Long reviewNo,Integer star_score,String review_content,String fileName,
                             int likeCnt, String updDate,String memNick,String memImg, Long cafeNo,
                             String cafeName, Boolean helps){
        this.reviewNo = reviewNo;
        this.star_score = star_score;
        this.review_content = review_content;
        this.likeCnt = likeCnt;
        this.updDate = updDate;
        this.memNick = memNick;
        this.fileName = fileName;
        this.memImg = memImg;
        this.cafeNo = cafeNo;
        this.cafeName = cafeName;
        this.helps = helps;
    }
}
