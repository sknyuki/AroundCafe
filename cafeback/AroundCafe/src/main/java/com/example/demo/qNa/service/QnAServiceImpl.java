package com.example.demo.qNa.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
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

//    @Autowired
//    MemberRoleRepository roleRepository;

    @Override
    public void includeImgregister(Integer membNo, QnADto info, String fileName) {
        Optional<Member> findMember = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMember.get();

        Long writer = Long.valueOf(membNo);

        Optional<Cafe> findCafe = cafeRepository.findById(info.getReceived_no());
        Cafe cafe = findCafe.get();
        QnA qnA = null;

        if(info.getReceived_no() != 0) {
            qnA = QnA.builder()
                    .received_no(info.getReceived_no())
                    .member(member)
                    .type(info.getType())
                    .received_name(cafe.getCafe_name())
                    .received_img(member.getMemImg())
                    .build();

            repository.save(qnA);
        }else {
            qnA = QnA.builder()
                    .received_no(info.getReceived_no())
                    .member(member)
                    .type(info.getType())
                    .received_name("관리자")
                    .received_img(member.getMemImg())
                    .build();
        }


        QnAComment comment = QnAComment.builder()
                .writer(writer)
                .content(info.getContent())
                .img(fileName)
                .qnA(qnA)
                .build();

        commentRepository.save(comment);
    }

    @Override
    public void exceptImgRegister(Integer membNo, QnADto info) {
        Optional<Member> findMember = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMember.get();

        Long writer = Long.valueOf(membNo);

        Optional<Cafe> findCafe = cafeRepository.findById(info.getReceived_no());
        Cafe cafe = findCafe.get();
        QnA qnA = null;

        if(info.getReceived_no() != 0){
            qnA = QnA.builder()
                    .received_no(info.getReceived_no())
                    .member(member)
                    .type(info.getType())
                    .received_name(cafe.getCafe_name())
                    .received_img(member.getMemImg())
                    .build();

            repository.save(qnA);
        }else {
            qnA = QnA.builder()
                    .received_no(info.getReceived_no())
                    .member(member)
                    .type(info.getType())
                    .received_name("관리자")
                    .received_img(member.getMemImg())
                    .build();
        }


        QnAComment comment = QnAComment.builder()
                .writer(Long.valueOf(writer))
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
        Optional<Member> findMember = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMember.get(); //멤버에서 이미지 찾기 위해 사용
        List<QnAResponse> comments = new ArrayList<>(); //response를 위한 리스트를 만듦
        List<QnA> qnAS = repository.findByMemberInfo(Long.valueOf(membNo));//qna에 대한 내용 확인을 위하 리스트



        for(int i = 0 ; i<qnAS.size(); i++ ) { //qna에 대한 리스트에서 for문을 돌면서 내용을 찾음
            QnA findQna = qnAS.get(i);

            List<QnAComment> qnAComments = commentRepository.findByImg(Math.toIntExact(findQna.getQna_no()));
            QnAComment comment = qnAComments.get(0); //0번째가 desc라서 제일 최신것임.

            QnAResponse response = QnAResponse.builder()
                    .qna_no(findQna.getQna_no())
                    .received_no(findQna.getReceived_no())
                    .received_img(findQna.getReceived_img())
                    .type(findQna.getType())
                    .received_name(findQna.getReceived_name())
                    .writer(comment.getWriter())
                    .content(comment.getContent())
                    .regTime(comment.getRegTime())
                    .regYear(comment.getRegYear())
                    .received_img(member.getMemImg())
                    .build();

            comments.add(response);
        }


        return comments;
    }
}
