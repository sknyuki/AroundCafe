package com.example.demo.mypage.cafe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cafe")
public class  Cafe {
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

    @PrePersist
    public void onPrePersist(){
        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "cafe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<CafeImg> cafeImgs = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "cafe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<CafeMenu> cafeMenus = new HashSet<>();

    /*
    추가해야할 것
    +member와의 조인
     */
}