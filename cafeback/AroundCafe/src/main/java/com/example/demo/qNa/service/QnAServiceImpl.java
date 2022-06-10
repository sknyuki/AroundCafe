package com.example.demo.qNa.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.qNa.dto.QnADto;
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

        Integer writer = 1;
//        Optional<MemberRole> role = roleRepository.findByRole(Long.valueOf(membNo));
//        MemberRole role1 = role.get();
//
//        if(role1.getName(). equals("회원")){
//            writer = 1;
//        }else if(role1.getName().equals("카페")) {
//            writer = 2;
//        }else {
//            writer = 0;
//        }

        Optional<Cafe> findCafe = cafeRepository.findById(info.getReceived_no());
        Cafe cafe = findCafe.get();
        QnA qnA = null;
        if(writer == 1){
            qnA = QnA.builder()
                    .received_no(info.getReceived_no())
                    .member(member)
                    .type(info.getType())
                    .cafe_name(cafe.getCafe_name())
                    .serverCheck(true)
                    .build();

            repository.save(qnA);
        }else {
            qnA = QnA.builder()
                    .received_no(info.getReceived_no())
                    .member(member)
                    .type(info.getType())
                    .cafe_name(cafe.getCafe_name())
                    .notServerCheck(true)
                    .build();

            repository.save(qnA);
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

        Integer writer = 1;
//        Optional<MemberRole> role = roleRepository.findByRole(Long.valueOf(membNo));
//        MemberRole role1 = role.get();
//
//        if(role1.getName().equals("회원")){
//            writer = 1;
//        }else if(role1.getName().equals("카페")) {
//            writer = 2;
//        }else {
//            writer = 0;
//        }

        Optional<Cafe> findCafe = cafeRepository.findById(info.getReceived_no());
        Cafe cafe = findCafe.get();
        QnA qnA = null;
        if(writer == 1){
            qnA = QnA.builder()
                    .received_no(info.getReceived_no())
                    .member(member)
                    .type(info.getType())
                    .cafe_name(cafe.getCafe_name())
                    .serverCheck(true)
                    .build();

            repository.save(qnA);
        }else {
            qnA = QnA.builder()
                    .received_no(info.getReceived_no())
                    .member(member)
                    .type(info.getType())
                    .cafe_name(cafe.getCafe_name())
                    .notServerCheck(true)
                    .build();

            repository.save(qnA);
        }

        QnAComment comment = QnAComment.builder()
                .writer(writer)
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
}
