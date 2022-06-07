package com.example.demo.qNa.service;

import com.example.demo.qNa.dto.QnADto;

public interface QnAService {
    public void includeImgregister(Integer membNo, QnADto info, String fileName);

    public void exceptImgRegister(Integer membNo, QnADto info);
}
