package com.example.demo.qNa.service;

import com.example.demo.qNa.dto.QnACommentDto;

public interface QnACommentService {
    public void exceptImgRegister(Integer membNo, QnACommentDto info);

    void saveImg(Integer qnaNo, String fileName);

//    void saveQna(Integer membNo, QnACommentDto info);
//
//    void saveQnaComment(QnACommentDto info);
}
