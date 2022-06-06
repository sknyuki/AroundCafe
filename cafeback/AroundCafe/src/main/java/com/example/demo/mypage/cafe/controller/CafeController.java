package com.example.demo.mypage.cafe.controller;

import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeImgTable;
import com.example.demo.mypage.cafe.service.cafe.CafeService;
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
@RequestMapping("/cafe")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CafeController {

    @Autowired
    private CafeService service;

    @ResponseBody
    @PutMapping(value = "/modify/{membNo}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String  cafeRegister (@PathVariable("membNo") Integer membNo,
                              @RequestPart(value = "info", required = false) Cafe info,
                              @RequestPart(value = "fileList", required = false)List<MultipartFile> fileList) throws IOException {

        log.info("uploadContents()" + info);
        log.info("uploadImg()"+fileList);

        service.notIncludeFileModifyCafe(membNo,info);
        log.info("***controller -> info is saved!!");

        if(fileList != null) {
            service.checkSavedImg(info.getCafeNo());
            log.info("***controller -> delete complete saved img!!");
            try{
                for(MultipartFile multipartFile : fileList) {
                    log.info("requestFile() - Make file :" +
                            multipartFile.getOriginalFilename());

                    FileOutputStream writer = new FileOutputStream(
                            "../../cafefront/around_cafe/src/assets/cafe/cafeMypage/" + info.getCafe_name() + "."+ multipartFile.getOriginalFilename());

                    writer.write(multipartFile.getBytes());
                    writer.close();
                    log.info("***file is saved at the vue file");

                    String saveImg = info.getCafe_name() + "."+ multipartFile.getOriginalFilename();

                    service.includeFileModifyCafe(info.getCafeNo(),saveImg);

                }
            } catch (Exception e) {
                return "register failed!";
            }
        }

        log.info("requestUpload(): Success!!!");
        return "등록이 완료되었습니다!";
    }

//    @GetMapping("/mypage/read/{membNo}")
//    public Cafe myPageRead(@PathVariable("membNo") Integer membNo) {
//        log.info("read Page no : "+ membNo);
//        return service.cafeMypageread(membNo);
//    }

    @GetMapping("/mypage/read")
    public Cafe myPageRead1() {
        log.info("read Page no : ");
        return service.read();
    }

    @GetMapping("/mypageImg/{cafeNo}")
    public List<CafeImgTable> readCafeImgTable(@PathVariable("cafeNo") Integer cafeNo) {
        log.info("read cafe img list");
        return service.imgList(cafeNo);
    }
}
