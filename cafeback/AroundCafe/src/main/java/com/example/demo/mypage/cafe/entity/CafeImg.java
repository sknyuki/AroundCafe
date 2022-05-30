package com.example.demo.mypage.cafe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class CafeImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long CafeImgNo;

    @Column(length = 128, nullable = true)
    private String cafe_img;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cafe_no")
    private Cafe cafe;
}