package com.example.demo.mypage.cafe.entity;

import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

    @Data
    @Entity
    @NoArgsConstructor
    public class Basket {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long shop_no;

        @Column(length = 32, nullable = false)
        private String shop_menu;

        @Column(length = 32, nullable = false)
        private String shop_cafe;

        @Column(length = 32, nullable = false)
        private String shop_price;

        @Column(length = 128, nullable = true)
        private String menu_img;

        @Column(length = 32, nullable = true)
        private String menu_qun;

        @CreatedDate
        @Column(length = 128, nullable = true)
        private String regDate;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "memNo")
        @JsonIgnore
        private Member member;


        @Builder
        public Basket(String shop_menu, String shop_cafe, String file, String shop_price, String menu_qun) {
            this.shop_menu = shop_menu;
            this.shop_cafe = shop_cafe;
            menu_img = file;
            this.shop_price = shop_price;
            this.menu_qun = menu_qun;
        }

        public Basket(String shop_menu, String shop_cafe, String shop_price, String menu_qun) {
            this.shop_menu = shop_menu;
            this.shop_cafe = shop_cafe;
            this.shop_price = shop_price;
            this.menu_qun = menu_qun;
        }
    }