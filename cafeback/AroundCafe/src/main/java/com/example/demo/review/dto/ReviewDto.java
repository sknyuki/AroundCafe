package com.example.demo.review.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long reviewNo;
    private String star_score;
    private String review_content;
    private String fileName;
    private Date regDate;
    private Date updDate;
    private Long cafeNum;

}
