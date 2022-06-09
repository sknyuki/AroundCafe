package com.example.demo.qNa.service;

import com.example.demo.qNa.dto.QnACommentDto;

public interface QnACommentService {
    public void exceptImgRegister(Integer qnaNo, QnACommentDto info);

    void saveImg(Integer qnaNo, String fileName);
}
