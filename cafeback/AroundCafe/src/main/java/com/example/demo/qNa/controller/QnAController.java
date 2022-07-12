package com.example.demo.qNa.controller;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qNa.dto.QnACommentDto;
import com.example.demo.qNa.dto.QnADto;
import com.example.demo.qNa.dto.QnAResponse;
import com.example.demo.qNa.entity.QnA;
import com.example.demo.qNa.entity.QnAComment;
import com.example.demo.qNa.service.QnAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/qna")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QnAController {

    @Autowired
    QnAService service;


    @ResponseBody
    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String qnaRegister(
                              @RequestPart(value = "info", required = false) QnADto info,
                              @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList) {
        log.info("member no :" + info.getMemNo());
        log.info("qna info : " +info.getReceived_no()+"/" +
                info.getContent());


        if(fileList != null) {
            try {
                for(MultipartFile multipartFile : fileList) {
                    String fileName = info.getMemNo() + "." + multipartFile.getOriginalFilename();
                    log.info("file name is : " +fileName);

                    FileOutputStream writer = new FileOutputStream(
                            "../../cafefront/around_cafe/src/assets/qna/" + fileName);
                    writer.write(multipartFile.getBytes());
                    writer.close();
                    service.includeImgregister(info, fileName);
                }
            }catch (IOException e) {
                return "문의글 등록이 실패되었습니다.";
            }
        }else {
            service.exceptImgRegister(info);
        }

        return "문의글이 등록되었습니다!";
    }

    @GetMapping("/memberRead/{checkQnaNo}")
    public List<QnAComment> readListPage(@PathVariable("checkQnaNo") Integer checkQnaNo) {
        return service.readQnA(checkQnaNo);
    }


    @GetMapping("/memberList/{membNo}")
    public List<QnAResponse> readQnaComment(@PathVariable("membNo") Integer membNo) {
        return service.responseQnAList(membNo);
    }

    @GetMapping("/admin/memberList/{membNo}")
    public List<QnAResponse> readAdminQnaComment(@PathVariable("membNo") Integer membNo) {
        return service.responseAdminQnAList(membNo);
    }

    @DeleteMapping("/delete/{qnaNo}")
    public void deleteQna(@PathVariable("qnaNo") Integer qnaNo) {
        log.info("delete qna No :" + qnaNo);

        service.deleteQna(qnaNo);
    }

}
