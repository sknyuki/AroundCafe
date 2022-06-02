package com.example.demo.mypage.cafe.entity;

import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(length = 32, nullable = false)
    private String cafe_bis_no;

    @Column(length = 128, nullable = true)
    private String cafe_time;

    @Column(length = 128, nullable = true)
    private String cafe_content;

    @Column(length = 32, nullable = true)
    private String cafe_call;

    @Column(length = 32, nullable = true)
    private String cafe_adr1;

    @Column(length = 32, nullable = true)
    private String cafe_adr2;

    @Column(length = 128, nullable = true)
    private String cafe_adr3;

    @CreatedDate
    @Column(length = 128, nullable = true)
    private String regDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "cafe", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<CafeImg> cafeImgs = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "member_no")
    private Member memberInfo;

    public Cafe(String cafe_name, String cafe_bis_no, String cafe_time, String cafe_content, String cafe_call, String cafe_adr1, String cafe_adr2, String cafe_adr3, Member member) {
        this.cafe_name = cafe_name;
        this.cafe_bis_no = cafe_bis_no;
        this.cafe_time = cafe_time;
        this.cafe_content = cafe_content;
        this.cafe_call = cafe_call;
        this.cafe_adr1 = cafe_adr1;
        this.cafe_adr2 = cafe_adr2;
        this.cafe_adr3 = cafe_adr3;
        memberInfo = member;
    }

    public Cafe(String cafe_name, String cafe_bis_no, String cafe_time, Member member) {
        this.cafe_name = cafe_name;
        this.cafe_bis_no = cafe_bis_no;
        this.cafe_time = cafe_time;
        memberInfo = member;
    }
}