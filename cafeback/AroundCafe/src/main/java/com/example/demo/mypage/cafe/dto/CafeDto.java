package com.example.demo.mypage.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeDto {
    private String id;
    private String cafe_name;
    private String cafe_time;
    private String cafe_call;
    private String cafe_adr1;
    private String cafe_adr2;
    private String cafe_adr3;

}