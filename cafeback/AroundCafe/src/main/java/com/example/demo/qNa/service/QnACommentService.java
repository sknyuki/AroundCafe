package com.example.demo.qNa.service;

import com.example.demo.qNa.dto.QnACommentDto;
import com.example.demo.qNa.entity.QnAComment;

import java.io.IOException;
import java.util.List;

public interface QnACommentService {
    public void exceptImgRegister(Integer membNo, QnACommentDto info);

    public void saveImg(Integer qnaNo, Integer membNo, String fileName);

    public List<String> dateList(Integer qnaNo);

    public void deleteComment(Integer qnaCommentNo) throws IOException;


//    void saveQna(Integer membNo, QnACommentDto info);
//
//    void saveQnaComment(QnACommentDto info);
}
