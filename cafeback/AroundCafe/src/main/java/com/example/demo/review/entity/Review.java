package com.example.demo.review.entity;

import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "review")
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

    @Formula("(SELECT count(1) FROM review_like r WHERE r.review_no = review_no)")
    private int likeCnt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_no")
    private Member memberInfo;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties({"reviews", "review"})
    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ReviewLike> reviewLike = new HashSet<>();


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
