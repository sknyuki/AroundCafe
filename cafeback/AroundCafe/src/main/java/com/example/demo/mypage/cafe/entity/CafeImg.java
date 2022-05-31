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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_no")
    @JsonIgnore
    private Cafe cafe;

    public CafeImg(String img, Cafe cafe1) {
        cafe_img = img;
        cafe = cafe1;
    }
}
