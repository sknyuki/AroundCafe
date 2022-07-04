package com.example.demo.qNa.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.qNa.dto.QnACommentDto;
import com.example.demo.qNa.dto.QnADto;
import com.example.demo.qNa.dto.QnAResponse;
import com.example.demo.qNa.entity.QnA;
import com.example.demo.qNa.entity.QnAComment;
import com.example.demo.qNa.repository.QnACommentRepository;
import com.example.demo.qNa.repository.QnARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnAServiceImpl implements QnAService {

    @Autowired
    QnARepository repository;

    @Autowired
    QnACommentRepository commentRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CafeRepository cafeRepository;

    @Override
    public void includeImgregister(QnADto info, String fileName) {
        Member member = memberRepository.findById(info.getMemNo()).orElseGet(null);
        Member revceivedNo = memberRepository.findById(info.getReceived_no()).orElseGet(null);
        QnA qnA = null;

        if(info.getReceived_no() != 0) {
            qnA = QnA.builder()
                    .received_no(revceivedNo.getMemNo())
                    .member(member)
                    .type(info.getType())
                    .build();

            repository.save(qnA);
        }else {
            qnA = QnA.builder()
                    .received_no(revceivedNo.getMemNo())
                    .member(member)
                    .type(info.getType())
                    .received_name("관리자")
                    .build();
            repository.save(qnA);
        }


        QnAComment comment = QnAComment.builder()
                .writer(info.getMemNo())
                .content(info.getContent())
                .img(fileName)
                .qnA(qnA)
                .build();

        commentRepository.save(comment);
    }

    @Override
    public void exceptImgRegister(QnADto info) {
        Member member = memberRepository.findById(Long.valueOf(info.getMemNo())).orElseGet(null);
        Member revceivedNo = memberRepository.findByIdFromCafeNo(info.getReceived_no()).orElseGet(null);
        QnA qnA = null;

        if(info.getReceived_no() != 0){
            qnA = QnA.builder()
                    .received_no(revceivedNo.getMemNo())
                    .member(member)
                    .type(info.getType())
                    .build();

            repository.save(qnA);
        }else {
            qnA = QnA.builder()
                    .received_no(revceivedNo.getMemNo())
                    .member(member)
                    .type(info.getType())
                    .received_name("관리자")
                    .build();
            repository.save(qnA);
        }


        QnAComment comment = QnAComment.builder()
                .writer(info.getMemNo())
                .content(info.getContent())
                .qnA(qnA)
                .build();

        commentRepository.save(comment);

    }

    @Override
    public List<QnAComment> readQnA(Integer qnaNo) {
        return commentRepository.findByQnA(qnaNo);
    }

    @Override
    public List<QnA> QnAList(Integer membNo) {
        return repository.findByMemberInfo(Long.valueOf(membNo));
    }

    @Override
    public List<QnAResponse> responseQnAList(Integer membNo) {
        Member member = memberRepository.findById(Long.valueOf(membNo)).orElseGet(null);
        String role = member.getRole().getName().getValue();
        log.info("role = " + role);
        List<QnAResponse> comments = new ArrayList<>();
        List<QnA> qnAS = null;

        if(role.equals("CAFE")){
            qnAS = repository.findByReceived_no(Long.valueOf(membNo));
        }else if (role.equals("USER")){
            qnAS = repository.findByMemberInfo(Long.valueOf(membNo));
        }

        if(qnAS.size() == 0){
            return null;
        }else
        for(int i = 0 ; i<qnAS.size(); i++ ) { //qna에 대한 리스트에서 for문을 돌면서 내용을 찾음
            QnA findQna = qnAS.get(i);
            Member orderMem = null;
            if(role.equals("CAFE")){
                orderMem = memberRepository.findById(findQna.getMemberInfo().getMemNo()).orElseGet(null);
            }else if (role.equals("USER")){
                orderMem  = memberRepository.findById(findQna.getReceived_no()).orElseGet(null);
            }

            List<QnAComment> qnAComments = commentRepository.findByImg(Math.toIntExact(findQna.getQna_no()));
            QnAComment comment = qnAComments.get(0); //0번째가 desc라서 제일 최신것임.

            QnAResponse response = QnAResponse.builder()
                    .qna_no(findQna.getQna_no())
                    .type(findQna.getType())
                    .received_no(orderMem.getMemNo())
                    .received_name(orderMem.getMemNick())
                    .received_img(orderMem.getMemImg())
                    .writer(Long.valueOf(membNo))
                    .writer_name(member.getMemNick())
                    .writerImg(member.getMemImg())
                    .content(comment.getContent())
                    .regTime(comment.getRegTime())
                    .regYear(comment.getRegYear())

                    .build();

            comments.add(response);
        }
        return comments;
    }



    @Override
    public void deleteQna(Integer qnaNo) {
        commentRepository.deleteAllByQnA(Long.valueOf(qnaNo));
        log.info("delete qna comment ");

        repository.deleteAllById(Long.valueOf(qnaNo));
        log.info("delete qna no : " + qnaNo);

    }

}
