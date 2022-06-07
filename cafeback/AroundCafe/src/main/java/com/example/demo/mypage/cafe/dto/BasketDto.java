package com.example.demo.mypage.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private Long mem_no;
    private String shop_name;
    private String shop_menu;
    private String shop_qun;
    private String shop_price;
    private String shop_cafe;
    private Date regDate;
}
