package com.example.demo.mypage.cafe.dto;

import com.example.demo.mypage.cafe.entity.CafeImgTable;
import com.example.demo.mypage.cafe.entity.CafeLike;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
public class CafeStarAverResponse {
    private Long cafeNo;
    private String cafe_name;
    private String cafe_bis_no;
    private String cafe_time;
    private String cafe_content;
    private String cafe_call;
    private String post;
    private String address;
    private Date regDate;
    Set<CafeImgTable> cafeImgs;
    Set<CafeMenu> cafeMenu;
    private Set<CafeLike> cafeLike;
    private double starAver;
}
