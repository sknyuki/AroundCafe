package com.example.demo.mypage.cafe.entity;

import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

    @Data
    @Entity
    @NoArgsConstructor
    public class Basket {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Basket_no;

        @Column(length = 32, nullable = false)
        private String shop_cafe;

        @Column(length = 32, nullable = false)
        private String shop_menu;

        @Column(length = 128, nullable = true)
        private String shop_qun;

        @Column(length = 128, nullable = true)
        private String shop_charge;


        @OneToOne
        @JsonIgnore
        @JoinColumn(name = "member_no")
        private Member memberInfo;

        public Basket(String shop_cafe, String shop_menu, String shop_qun, String shop_charge, Member member) {
            this.shop_cafe = shop_cafe;
            this.shop_menu = shop_menu;
            this.shop_qun = shop_qun;
            this.shop_charge = shop_charge;;
            memberInfo = member;
        }
    }
