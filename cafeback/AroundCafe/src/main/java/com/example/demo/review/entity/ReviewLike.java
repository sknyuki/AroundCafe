
package com.example.demo.review.entity;

import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_like")
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="help_no")
    private Long helpNo;


    @JsonIgnoreProperties({"member"})
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name= "review_no")
    @JsonIgnore
    private Review review;

    @JsonBackReference
    @JsonIgnoreProperties({"reviewLike", "review"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "member_no")
    private Member member;


    /*
    @Builder
    public ReviewLike(Review review, String writer) {
        this.writer=writer;
        review_info = review;
    }

     */


}
/*
package com.example.demo.review.entity;

        import com.example.demo.member.entity.Member;
        import com.fasterxml.jackson.annotation.JsonBackReference;
        import com.fasterxml.jackson.annotation.JsonIgnore;
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import javax.persistence.*;
        import javax.transaction.Transactional;
        import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_like")
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="help_no")
    private Long helpNo;


    @JsonIgnoreProperties({"member"})
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name= "review_no")
    @JsonIgnore
    private Review review;

    @JsonBackReference
    @JsonIgnoreProperties({"reviewLike", "review"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "member_no")
    private Member member;


    /*
    @Builder
    public ReviewLike(Review review, String writer) {
        this.writer=writer;
        review_info = review;
    }




}
*/







