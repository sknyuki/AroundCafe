package com.example.demo.qNa.service;

import com.example.demo.qNa.dto.QnACommentDto;
import com.example.demo.qNa.entity.QnAComment;

import java.util.List;

public interface QnACommentService {
    public void exceptImgRegister(Integer membNo, QnACommentDto info);

    void saveImg(Integer qnaNo, String fileName);

    List<String> dateList(Integer qnaNo);

//    void saveQna(Integer membNo, QnACommentDto info);
//
//    void saveQnaComment(QnACommentDto info);
}
