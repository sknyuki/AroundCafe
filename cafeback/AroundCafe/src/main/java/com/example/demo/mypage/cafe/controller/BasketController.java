package com.example.demo.Controller;



import com.example.demo.mypage.cafe.dto.BasketDto;
import com.example.demo.mypage.cafe.entity.Basket;
import com.example.demo.mypage.cafe.service.basket.BasketService;
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
@RequestMapping("/BasketBoard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class BasketController {

    @Autowired
    private BasketService service;


    @ResponseBody
    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String BasketRegister(@RequestPart(value = "info", required = false) BasketDto info,
                               @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList) {
        log.info("menu info:" + info);
        log.info("file name:" + fileList);

        if(fileList != null) {
            try{
                for(MultipartFile multipartFile: fileList) {
                    log.info("requestUploadFile() - Make file: " +
                            multipartFile.getOriginalFilename());
                    FileOutputStream writer = new FileOutputStream(
                            "../../cafefront/around_cafe/src/assets/basket/"  + multipartFile.getOriginalFilename());
                    log.info("save complete!");

                    writer.write(multipartFile.getBytes());
                    writer.close();
                    service.includeImgSave(info, multipartFile.getOriginalFilename());
                }

            } catch (Exception e) {
                return "review fail!";
            }
        }else if(fileList == null) {
            service.exceptImgSave(info);
        }

        return "메뉴 등록을 성공했습니다!";
    }

    @GetMapping("/list/{membNo}")
    public List<Basket> menuList(@PathVariable("membNo") Integer membNo) {
        log.info("get menu list");

        return service.list(membNo);
    }

    @DeleteMapping("/delete/{ShopNo}")
    public void deleteMenu (@PathVariable("menuNo") Integer menuNo) throws IOException {
        log.info("delete no : "+menuNo);

        service.delete(menuNo);
    }

}
