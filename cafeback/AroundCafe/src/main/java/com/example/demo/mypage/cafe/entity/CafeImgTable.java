package com.example.demo.mypage.cafe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class CafeImgTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long CafeImgNo;

    @Column(length = 128, nullable = true)
    private String cafe_img;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_no")
    @JsonIgnore
    private Cafe cafe;

    @CreatedDate
    @Column(length = 128, nullable = true)
    private String regDate;

    @Builder
    public CafeImgTable(String cafe_img, Cafe cafe){
        this.cafe_img = cafe_img;
        this.cafe = cafe;
    }
}