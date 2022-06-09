package com.example.demo.qNa.entity;

import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@RequiredArgsConstructor
@Entity
public class QnAComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qna_comment_no;

    @Column
    private Integer writer;

    @Column(length = 128, nullable = true)
    private String content;

    @Column(length = 128, nullable = true)
    private String img;

    @CreationTimestamp
    private Date regDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "qna_no")
    private QnA qnA;

    @Builder
    public QnAComment (Integer writer, String content, String img, QnA qnA){
        this.writer = writer;
        this.content = content;
        this.img = img;
        this.qnA = qnA;
    }
}
