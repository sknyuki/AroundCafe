package com.example.demo.qNa.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.MemberRoleRepository;
import com.example.demo.qNa.dto.QnADto;
import com.example.demo.qNa.entity.QnA;
import com.example.demo.qNa.entity.QnAComment;
import com.example.demo.qNa.repository.QnACommentRepository;
import com.example.demo.qNa.repository.QnARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    MemberRoleRepository roleRepository;

    @Override
    public void includeImgregister(Integer membNo, QnADto info, String fileName) {
        Optional<Member> findMember = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMember.get();

        QnA qnA = QnA.builder()
                .received_no(info.getReceived_no())
                .member(member)
                .build();

        repository.save(qnA);

        Integer writer;
        Optional<MemberRole> role = roleRepository.findByRole(membNo);
        MemberRole role1 = role.get();

        if(role1.getName(). equals("회원")){
            writer = 1;
        }else if(role1.getName().equals("카페")) {
            writer = 2;
        }else {
            writer = 0;
        }

        QnAComment comment = QnAComment.builder()
                .writer(writer)
                .content(info.getContent())
                .img(fileName)
                .build();

        commentRepository.save(comment);
    }

    @Override
    public void exceptImgRegister(Integer membNo, QnADto info) {
        Optional<Member> findMember = memberRepository.findById(Long.valueOf(membNo));
        Member member = findMember.get();

        QnA qnA = QnA.builder()
                .received_no(info.getReceived_no())
                .member(member)
                .build();

        repository.save(qnA);

        Integer writer;
        Optional<MemberRole> role = roleRepository.findByRole(membNo);
        MemberRole role1 = role.get();

        if(role1.getName(). equals("회원")){
            writer = 1;
        }else if(role1.getName().equals("카페")) {
            writer = 2;
        }else {
            writer = 0;
        }

        QnAComment comment = QnAComment.builder()
                .writer(writer)
                .content(info.getContent())
                .build();

        commentRepository.save(comment);
    }
}
