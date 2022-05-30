package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    //Query 없어도 JpaRepository에서 기본적으로 findBy, existsBy를 지원
    Optional<Member> findByMemId(String memId);
    Optional<Member> findByPhoneNum(String phoneNum);
    Boolean existsByMemId(String memId);

}
