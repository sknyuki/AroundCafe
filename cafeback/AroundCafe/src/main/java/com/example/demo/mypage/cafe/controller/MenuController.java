package com.example.demo.mypage.cafe.controller;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import com.example.demo.mypage.cafe.service.menu.MenuService;
import com.example.demo.review.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MenuController {

    @Autowired
    MenuService service;

    @ResponseBody
    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String menuRegister(@RequestPart(value = "info", required = false) CafeMenuDto info,
                             @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList) {
        log.info("menu info : " + info);
        log.info("file name: " + fileList);


        if(fileList != null) {
            try{
                for(MultipartFile multipartFile: fileList) {
                    String fileName = info.getCafe_no() + "." + multipartFile.getOriginalFilename();
                    log.info("requestUploadFile() - Make file: " +
                            multipartFile.getOriginalFilename());
                    FileOutputStream writer = new FileOutputStream(
                            "../../cafefront/around_cafe/src/assets/cafe/cafeMenu/"  + fileName);
                    log.info("save complete!");

                    writer.write(multipartFile.getBytes());
                    writer.close();
                    service.includeImgSave(info, fileName);
                }

            } catch (Exception e) {
                return "review fail!";
            }
        }else if(fileList == null) {
            service.exceptImgSave(info);
        }

        return "메뉴 등록을 성공했습니다!";
    }

    @ResponseBody
    @PutMapping(value = "/modify/{memNo}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String menuModify(@PathVariable("memNo") Integer memNo,
                               @RequestPart(value = "info", required = false) CafeMenu info,
                               @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList) {

        log.info("member name : " + memNo);
        log.info("menu info : " +info);
        log.info("file name : " +fileList);

        if(fileList != null) {
            try{
                for(MultipartFile multipartFile : fileList) {
                    UUID uuid = UUID.randomUUID();
                    String fileName = uuid + "." + multipartFile.getOriginalFilename();

                    FileOutputStream writer = new FileOutputStream(
                            "../../cafefront/around_cafe/src/assets/cafe/cafeMenu/" + fileName);

                    writer.write(multipartFile.getBytes());
                    writer.close();
                    service.includeImgModify(info, fileName, memNo);
                }
            } catch (Exception e) {
                return "modify is failed.";
            }
        }else if (fileList == null) {
            service.exceptImgModify(info, memNo);
        }

        log.info("modify is complete");
        return "modify complete!!";
    }

    @GetMapping("/list/{memNo}")
    public List<CafeMenu> menuList(@PathVariable("memNo") Integer memNo) {
        log.info("get menu list, member no: " +memNo);

        return service.list(memNo);
    }

//
//    @GetMapping("/signatureList")
//    public List<CafeMenu> signatureList() {
//        log.info("get menu list");
//
//        return service.sigList();
//    }
//
//    @GetMapping("/soldOutList")
//    public List<CafeMenu> soldOutList() {
//        log.info("get menu list");
//
//        return service.soldList();
//    }

    @DeleteMapping("/delete/{menuNo}")
    public void deleteMenu (@PathVariable("menuNo") Integer menuNo) throws IOException {
        log.info("delete no : "+menuNo);

        service.delete(menuNo);
    }

    @PostMapping("/changeSignature/{menuNo}")
    public String registerSignature(@PathVariable("menuNo") Integer menuNo) {
        log.info("register signature");
        return service.changeSignature(menuNo);
    }


    @PostMapping("/changeSoldOut/{menuNo}")
    public String registerSoldOut(@PathVariable("menuNo") Integer menuNo) {
        log.info("register sold out");
        return service.changeSoldOut(menuNo);
    }


    @GetMapping("/findMenu/{memNo}/{cafe_name}")
    public List<CafeMenu> findMenu(@PathVariable("memNo")Integer memNo,
                                   @PathVariable("cafe_name")String cafe_name){
        log.info("find menu memNo -> " + memNo +" cafe name _" + cafe_name);
        return service.findMenu(memNo, cafe_name);
    }

}
