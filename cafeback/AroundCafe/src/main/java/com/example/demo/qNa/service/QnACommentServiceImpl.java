package com.example.demo.qNa.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.MemberRoleType;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qNa.dto.QnACommentDto;
import com.example.demo.qNa.entity.QnA;
import com.example.demo.qNa.entity.QnAComment;
import com.example.demo.qNa.repository.QnACommentRepository;
import com.example.demo.qNa.repository.QnARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnACommentServiceImpl implements QnACommentService{
    @Autowired
    QnACommentRepository repository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    QnARepository qnARepository;

//    @Override
//    public void saveQna(Integer membNo, QnACommentDto info) {
//        Optional<QnA> findQna = qnARepository.findById(Long.valueOf(info.getQnaNo()));
//        QnA qnA = findQna.get();
//
////        Optional<Member> findMember = memberRepository.findById(info.getMembNo());
////        Member member = findMember.get();
////        MemberRole findRole = member.getRole();
////        MemberRoleType name = findRole.getName();
////        String role = name.getValue();
////
////
////        if(role.equals("USER")) {
////            writer = 1;
////            qnA.builder().serverCheck(true).notServerCheck(false).build();
////
////        }else if(role.equals("CAFE")){
////            writer = 2;
////            qnA.builder().serverCheck(false).notServerCheck(true).build();
////        }else {
////            writer = 0;
////            qnA.builder().serverCheck(false).notServerCheck(true).build();
////        }
//
//
//        qnA.builder().serverCheck(true).notServerCheck(false).build();
//        qnARepository.save(qnA);
//    }
//
//    @Override
//    public void saveQnaComment(QnACommentDto info) {
//        List<QnAComment> findImg = repository.findByImg(Math.toIntExact(info.getQnaNo()));
//        QnAComment comment1 = findImg.get(0);
//        log.info("here is comment img ::" + comment1.getImg());
//
//        if(comment1.getWriter() == null) {
//            QnAComment comment = QnAComment.builder()
//                    .qna_comment_no(comment1.getQna_comment_no())
//                    .writer(writer)
//                    .content(info.getChatting())
//                    .img(comment1.getImg())
//                    .qnA(qnA)
//                    .regDate(comment1.getRegDate())
//                    .build();
//
//            repository.save(comment);
//        }else{
//            QnAComment comment = QnAComment.builder()
//                    .writer(writer)
//                    .content(info.getChatting())
//                    .qnA(qnA)
//                    .regDate(comment1.getRegDate())
//                    .build();
//
//            repository.save(comment);
//            log.info("save is success");
//    }

    @Override
    public void exceptImgRegister(Integer membNo, QnACommentDto info) {
            Optional<QnA> findQna = qnARepository.findById(Long.valueOf(info.getQnaNo()));
            QnA qnA = findQna.get();


//        Optional<Member> findMember = memberRepository.findById(info.getMembNo());
//        Member member = findMember.get();
//        MemberRole findRole = member.getRole();
//        MemberRoleType name = findRole.getName();
//        String role = name.getValue();
//
//
//        if(role.equals("USER")) {
//            writer = 1;
//            qnA.builder().serverCheck(true).notServerCheck(false).build();
//
//        }else if(role.equals("CAFE")){
//            writer = 2;
//            qnA.builder().serverCheck(false).notServerCheck(true).build();
//        }else {
//            writer = 0;
//            qnA.builder().serverCheck(false).notServerCheck(true).build();
//        }
        int writer = 2;

        if(writer == 1) {
            qnA.setServerCheck(true);
            qnA.setNotServerCheck(false);
            qnARepository.save(qnA);
//            qnA= QnA.builder().serverCheck(true).notServerCheck(false).build();
//            qnARepository.save(qnA);
        }else {
            qnA.setServerCheck(false);
            qnA.setNotServerCheck(true);
            qnARepository.save(qnA);
//            qnA= QnA.builder().serverCheck(false).notServerCheck(true).build();
//            qnARepository.save(qnA);
        }
        log.info("qna is saved!!! server :" + qnA.getServerCheck() + "not server : " + qnA.getNotServerCheck());



        List<QnAComment> findImg = repository.findByImg(Math.toIntExact(info.getQnaNo()));
        QnAComment comment1 = findImg.get(0);
        log.info("here is comment img ::" + comment1.getImg());

        if(comment1.getWriter() == null) {
            QnAComment comment = QnAComment.builder()
                    .qna_comment_no(comment1.getQna_comment_no())
                    .writer(writer)
                    .content(info.getChatting())
                    .img(comment1.getImg())
                    .qnA(qnA)
                    .regDate(comment1.getRegDate())
                    .build();

            repository.save(comment);
        }else{
            QnAComment comment = QnAComment.builder()
                    .writer(writer)
                    .content(info.getChatting())
                    .qnA(qnA)
                    .regDate(comment1.getRegDate())
                    .build();

            repository.save(comment);
            log.info("save is success");
        }
    }


    @Override
    public void saveImg(Integer qnaNo, String fileName) {
        Optional<QnA> findQna = qnARepository.findById(Long.valueOf(qnaNo));
        QnA qnA = findQna.get();

        QnAComment comment = QnAComment.builder()
                .img(fileName)
                .qnA(qnA)
                .build();

        repository.save(comment);
    }


}
