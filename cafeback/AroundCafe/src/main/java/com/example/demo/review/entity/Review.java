package com.example.demo.review.entity;

import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNo;

    @Column(length = 32, nullable = false)
    private String star_score;

    @Lob
    private String review_content;

    @Column
    private String fileName;  //이미지 이름 추가

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_no")
    private Member memberInfo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Date regDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private Date updDate;

    @Column(length = 32, nullable = false)
    private Long cafeNum;   //카페 번호를 vue 에서 넘길때 받아오는 것

    @Builder
    public Review(String star_score, String review_content,String fileName, Date regDate, Date updDate, Long cafeNum, Member member) {
        this.star_score = star_score;
        this.review_content = review_content;
        this.fileName = fileName;
        this.regDate = regDate;
        this.updDate = updDate;
        this.cafeNum = cafeNum;
        memberInfo = member;
    }

    public Review(String star_score, String review_content,String fileName, Long cafeNum) {
        this.star_score = star_score;
        this.review_content = review_content;
        this.fileName = fileName;
        this.cafeNum = cafeNum;
    }

}
