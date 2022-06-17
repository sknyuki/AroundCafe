
package com.example.demo.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long helpNo;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name= "review_no")
    @JsonIgnore
    private Review review_info;

    @Column
    private String writer;


    @Builder
    public ReviewLike(Review review, String writer) {
        this.writer=writer;
        review_info = review;
    }


}





