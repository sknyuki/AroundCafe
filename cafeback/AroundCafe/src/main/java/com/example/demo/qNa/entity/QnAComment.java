package com.example.demo.qNa.entity;

import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "qna_comment")
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

    @CreatedDate
    @Column(length = 128, nullable = true)
    private String regDate;

    @PrePersist
    public void onPrePersist(){
        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "qna_no")
    private QnA qnA;

    @Builder
    public QnAComment (Long qna_comment_no, Integer writer, String content, String img, QnA qnA, String regDate){
        this.qna_comment_no = qna_comment_no;
        this.writer = writer;
        this.content = content;
        this.img = img;
        this.qnA = qnA;
        this.regDate = regDate;
    }
}
