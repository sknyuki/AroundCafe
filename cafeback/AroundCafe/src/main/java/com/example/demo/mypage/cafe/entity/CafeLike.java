package com.example.demo.mypage.cafe.entity;

import com.example.demo.member.entity.Member;
import com.example.demo.review.entity.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cafe_like")
public class CafeLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="like_no")
    private Long likeNo;


    @JsonIgnoreProperties({"member"})
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name= "cafe_no")
    @JsonIgnore
    private Cafe cafe;


    @JsonBackReference
    @JsonIgnoreProperties({"cafeLike", "cafe"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "member_no")
    private Member member;


}
