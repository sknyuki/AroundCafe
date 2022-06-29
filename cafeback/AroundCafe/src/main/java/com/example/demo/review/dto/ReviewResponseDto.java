package com.example.demo.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReviewResponseDto {
    private Long reviewNo;
    private String star_score;
    private String review_content;
    private String fileName;
    private int likeCnt;
    private String updDate;
    private String memNick;
    private String memImg;

    @Builder
    public ReviewResponseDto(Long reviewNo,String star_score,String review_content,String fileName,
                             int likeCnt, String updDate,String memNick,String memImg ){
        this.reviewNo = reviewNo;
        this.star_score = star_score;
        this.review_content = review_content;
        this.likeCnt = likeCnt;
        this.updDate = updDate;
        this.memNick = memNick;
        this.memImg = memImg;
    }
}
