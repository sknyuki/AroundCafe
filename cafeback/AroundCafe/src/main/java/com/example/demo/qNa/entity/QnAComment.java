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
    private Long writer;

    @Column(length = 128, nullable = true)
    private String content;

    @Column(length = 128, nullable = true)
    private String img;

    @CreatedDate
    @Column(length = 128, nullable = true)
    private String regTime;

    @CreatedDate
    @Column(length = 128, nullable = true)
    private String regYear;

    @PrePersist
    public void onPrePersist(){
        this.regTime = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        this.regYear = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "qna_no")
    private QnA qnA;

    @Builder
    public QnAComment (Long qna_comment_no, Long writer, String content, String img, QnA qnA, String regTime, String regYear){
        this.qna_comment_no = qna_comment_no;
        this.writer = writer;
        this.content = content;
        this.img = img;
        this.qnA = qnA;
        this.regTime = regTime;
        this.regYear = regYear;
    }
}
