package com.example.demo.qNa.entity;

import com.example.demo.member.entity.Member;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@Entity
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qna_no;

    @Column(length = 32, nullable = true)
    private Long received_no;

    @CreationTimestamp
    private Date regDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_no")
    private Member memberInfo;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "qna", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<QnAComment> comments = new HashSet<>();

    @Builder
    public QnA (Long received_no, Member member){
        this.received_no = received_no;
        memberInfo = member;
    }

}
