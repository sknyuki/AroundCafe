package com.example.demo.mypage.cafe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class CafeMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menu_no;

    @Column(length = 32, nullable = false)
    private String menu_name;

    @Column(length = 32, nullable = false)
    private String menu_price;

    @Column(length = 128, nullable = true)
    private String menu_img;

    @Column(length = 128, nullable = true)
    private String menu_content;

    @Column(length = 32, columnDefinition = "varchar(20) default 1")
    private Integer per_menu_quantity;

    @Column(length=32,nullable = true)
    private Integer per_menu_total_price;

    @Column(columnDefinition = "boolean default false")
    private Boolean signature = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean sold_out = false;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafe_no")
    @JsonIgnore
    private Cafe cafe;


    @Builder
    public CafeMenu(String menu_name, String menu_price, String file, String menu_content, Integer per_menu_quantity,Integer per_menu_total_price,Cafe cafe) {
        this.menu_name = menu_name;
        this.menu_price = menu_price;
        this.menu_img = file;
        this.menu_content = menu_content;
        this.per_menu_quantity=per_menu_quantity;
        this.per_menu_total_price=per_menu_total_price;
        this.cafe = cafe;
    }
}
