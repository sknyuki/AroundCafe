package com.example.demo.mypage.cafe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cafe")
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cafeNo;

    @Column(length = 32, nullable = false)
    private String cafe_name;

    @Column(length = 128, nullable = false)
    private String cafe_time;

    @Column(length = 128, nullable = true)
    private String cafe_content;

    @Column(length = 32, nullable = false)
    private String cafe_call;

    @Column(length = 32, nullable = false)
    private String cafe_adr1;

    @Column(length = 32, nullable = false)
    private String cafe_adr2;

    @Column(length = 128, nullable = false)
    private String cafe_adr3;

    @CreatedDate
    @Column(length = 128, nullable = true)
    private String regDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "cafe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<CafeImg> cafeImgs = new ArrayList<>();

    /*
    추가해야할 것
    +member와의 조인
     */
}

