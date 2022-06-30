package com.example.demo.mypage.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeMenuDto {
    private Long cafe_no;
    private Long memNo;
    private String menu_name;
    private String menu_price;
    private String menu_content;
    private Integer per_menu_total_price;
    private Integer per_menu_quantity;
    private Boolean signature;
    private Boolean sold_out;
}