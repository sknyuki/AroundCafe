package com.example.demo.qNa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnADto {
    private Long memNo;
    private Long received_no;
    private String type;
    private String content;
}
