package com.example.demo.qNa.controller;

import com.example.demo.qNa.dto.QnACommentDto;
import com.example.demo.qNa.service.QnACommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qnaComment")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QnACommentController {

    @Autowired
    QnACommentService service;

    @ResponseBody
    @PostMapping(value = "/register/{membNo}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void qnaRegister(@PathVariable("membNo") Integer membNo,
                            @RequestPart(value = "info", required = false) QnACommentDto info ) {
        log.info("qna no :" + membNo);
        log.info("member no : " + info.getQnaNo() + "/" +
                info.getChatting());

        service.exceptImgRegister(membNo, info);

    }


    @ResponseBody
    @PostMapping(value = "/registerImg/{qnaNo}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void registerImg(@PathVariable("qnaNo") Integer qnaNo,
                            @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList) {
        log.info("qna no :" + qnaNo);
        log.info("??is that right??");


        try {
            for (MultipartFile multipartFile : fileList) {
                String fileName = qnaNo + "." + multipartFile.getOriginalFilename();
                log.info("file name is : " + fileName);

                FileOutputStream writer = new FileOutputStream(
                        "../../cafefront/around_cafe/src/assets/qna/" + fileName);
                writer.write(multipartFile.getBytes());
                writer.close();
                service.saveImg(qnaNo, fileName);
            }
        } catch (IOException e) {
        }
    }
}
