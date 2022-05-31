package com.example.demo.mypage.cafe.controller;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import com.example.demo.mypage.cafe.service.menu.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
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
                    FileOutputStream writer = new FileOutputStream(
                            "../../cafefront/around_cafe/src/asserts/cafeMenu" + info.getCafe_name() + "." + multipartFile.getOriginalFilename());
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

        return "upload complete!";
    }

    @ResponseBody
    @PutMapping(value = "/modify/{cafeName}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String menuModify(@PathVariable("cafeName") String cafeName,
                               @RequestPart(value = "info", required = false) CafeMenu info,
                               @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList) {

        log.info("cafe name : " + cafeName);
        log.info("cafe info : " +info);
        log.info("file name : " +fileList);

        if(fileList != null) {
            try{
                for(MultipartFile multipartFile : fileList) {
                    FileOutputStream writer = new FileOutputStream(
                            "../../cafefront/around_cafe/src/asserts/cafeMenu" + cafeName + "." + multipartFile.getOriginalFilename());

                    writer.write(multipartFile.getBytes());
                    writer.close();
                    service.includeImgModify(info, multipartFile.getOriginalFilename(), cafeName);
                }
            } catch (Exception e) {
                return "modify is failed.";
            }
        }else if (fileList == null) {
            service.exceptImgModify(info);
        }

        log.info("modify is complete");
        return "modify complete!!";
    }

    @GetMapping("/list/{membNo}")
    public List<CafeMenu> menuList(@PathVariable("membNo") Integer membNo) {
        log.info("get menu list");

        return service.list(membNo);
    }

}
