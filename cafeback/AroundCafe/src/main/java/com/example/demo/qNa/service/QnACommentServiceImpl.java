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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnACommentServiceImpl implements QnACommentService{
    @Autowired
    QnACommentRepository repository;

    @Autowired
    QnARepository qnARepository;


    @Override
    public void exceptImgRegister(Integer membNo, QnACommentDto info) {
        QnA qnA = qnARepository.findById(Long.valueOf(info.getQnaNo())).orElseGet(null);


        qnARepository.save(qnA);

        QnAComment comment = QnAComment.builder()
                .writer(Long.valueOf(membNo))
                .content(info.getChatting())
                .qnA(qnA)
                .build();

        repository.save(comment);
        log.info("save is success");
    }


    @Override
    public void saveImg(Integer qnaNo, Integer membNo,String fileName) {
        QnA qnA = qnARepository.findById(Long.valueOf(qnaNo)).orElseGet(null);

        QnAComment comment = QnAComment.builder()
                .writer(qnA.getMemberInfo().getMemNo())
                .img(fileName)
                .qnA(qnA)
                .build();

        repository.save(comment);
    }

    @Override
    public List<String> dateList(Integer qnaNo) {
        return repository.findByDate(qnaNo);
    }

    @Override
    public void deleteComment(Integer qnaCommentNo) throws IOException {
        log.info("qna comment no : " +qnaCommentNo);

        QnAComment comment = repository.findById(Long.valueOf(qnaCommentNo)).orElseGet(null);
        if(comment.getImg() != null) {
            log.info("file name : " +comment.getImg());
            Path file = Paths.get("../../cafefront/around_cafe/src/assets/qna/" + comment.getImg());
            Files.delete(file);
        }
        repository.deleteById(Long.valueOf(qnaCommentNo));
    }


}
