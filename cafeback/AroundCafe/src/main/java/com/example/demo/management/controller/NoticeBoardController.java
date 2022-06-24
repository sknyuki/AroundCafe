package com.example.demo.management.controller;


import com.example.demo.management.entity.NoticeBoard;
import com.example.demo.management.service.NoticeBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/noticeBoard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class NoticeBoardController {

    @Autowired
    private NoticeBoardService service;

    @PostMapping("/register")
    public void noticeBoardRegister(@Validated @RequestBody NoticeBoard noticeBoard){
        log.info("noticeBoardRegister()");

        service.register(noticeBoard);
    }

    @GetMapping("/list")
    public List<NoticeBoard> noticeBoardList(){
        log.info("noticeBoardList()");

        return service.list();
    }

    @GetMapping("/{boardNo}")
    public NoticeBoard noticeBoardRead(@PathVariable("boardNo")Long boardNo){
        log.info("noticeBoardRead()" +boardNo);
        service.updateViewCount(boardNo);
        log.info(service.read(boardNo).toString());
        return service.read(boardNo);
    }

    @PutMapping("/{boardNo}")
    public NoticeBoard noticeBoardModify(@PathVariable("boardNo")Long boardNo, @RequestBody NoticeBoard noticeBoard){
        log.info("noticeBoardModify()");

        noticeBoard.setBoardNo(boardNo);
        service.modify(noticeBoard);

        return noticeBoard;
    }

    @DeleteMapping("/{boardNo}")
    public void noticeBoardRemove(@PathVariable("boardNo") Long boardNo){
        log.info("noticeBoardRemove()");

        service.remove(boardNo);
    }
}
