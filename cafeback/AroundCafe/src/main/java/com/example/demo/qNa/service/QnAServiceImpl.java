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
        Member orderMem = null;
        if(info.getReceived_no() == null){
            orderMem = memberRepository.findByAdmin().orElseGet(null);
        }else {
            orderMem = memberRepository.findByIdFromCafeNo(info.getReceived_no()).orElseGet(null);
        }
        QnA qnA = null;
        qnA = QnA.builder()
                .received_no(orderMem.getMemNo())
                .member(member)
                .type(info.getType())
                .received_name(orderMem.getMemNick())
                .build();
        repository.save(qnA);


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
        Member member = memberRepository.findById(Long.valueOf(membNo)).orElseGet(null);
        return repository.findByMemberInfo(member);
    }

    @Override
    public List<QnAResponse> responseQnAList(Integer membNo) {
        Member member = memberRepository.findById(Long.valueOf(membNo)).orElseGet(null);
        String role = member.getRole().getName().getValue();

        List<QnA> qnAS = null;

        if(role.equals("USER")){
            qnAS = repository.findByMemberInfo(member);
        }else{
            qnAS = repository.findByReceived_no(Long.valueOf(membNo));
        }

        return getQnaList(qnAS, member);
    }

    @Override
    public List<QnAResponse> responseAdminQnAList(Integer membNo) {
        Member member = memberRepository.findById(Long.valueOf(membNo)).orElseGet(null);
        Member admin = memberRepository.findByAdmin().orElseGet(null);
        String role = member.getRole().getName().getValue();

        List<QnA> qnAS = null;

        if(role.equals("ADMIN")){
            qnAS = repository.findByMemberInfoByAdmin(Long.valueOf(membNo));
        }else{
            qnAS = repository.findByReceived_noByAdmin(member, admin.getMemNo());
        }

        return getQnaList(qnAS, member);
    }

    private List<QnAResponse> getQnaList(List<QnA> qnAS, Member member) {
        List<QnAResponse> comments = new ArrayList<>();
        if(qnAS.size() > 0){
            for(QnA findQna : qnAS) { //qna에 대한 리스트에서 for문을 돌면서 내용을 찾음
                Member orderMem = null;
                if(findQna.getReceived_no() == member.getMemNo()){
                    orderMem = memberRepository.findById(findQna.getMemberInfo().getMemNo()).orElseGet(null);
                }else {
                    orderMem = memberRepository.findById(findQna.getReceived_no()).orElseGet(null);
                }
                QnAComment comment = commentRepository.findByRecentComment(findQna).orElseGet(null);

                QnAResponse response = QnAResponse.builder()
                        .qna_no(findQna.getQna_no())
                        .type(findQna.getType())
                        .received_no(orderMem.getMemNo())
                        .received_name(orderMem.getMemNick())
                        .received_img(orderMem.getMemImg())
                        .received_socialType(orderMem.getSocialType().getName())
                        .writer(member.getMemNo())
                        .writer_name(member.getMemNick())
                        .writerImg(member.getMemImg())
                        .content(comment.getContent())
                        .build();
                comments.add(response);
            }
            return comments;
        }else return null;
    }


    @Override
    public void deleteQna(Integer qnaNo) {
        commentRepository.deleteAllByQnA(Long.valueOf(qnaNo));
        log.info("delete qna comment ");

        repository.deleteAllById(Long.valueOf(qnaNo));
        log.info("delete qna no : " + qnaNo);

    }

    @Override
    public QnAComment test(Integer qnaNo) {
        QnA qnA = repository.findById(Long.valueOf(qnaNo)).orElseGet(null);
        return commentRepository.findByRecentComment(qnA).orElseGet(null);
    }

}
