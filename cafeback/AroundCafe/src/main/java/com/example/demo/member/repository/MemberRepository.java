package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemId(String memId);
    Optional<Member> findByMemNo(Long memNo);
    Optional<Member> findByMemNick(String memNick);
    Optional<Member> findBySocialNo(String socialNo);
    Optional<Member> findByPhoneNum(String phoneNum);
    Boolean existsByMemId(String memId);
    Boolean existsBySocialNo(String socialNo);
    Boolean existsByMemNick(String memNick);

    @Modifying
    @Query("update Member m set m.memPoint = :memPoint where m.memNo = :memNo")
    void updateMemberPoint(@Param("memNo") Long memNo, @Param("memPoint") Integer memPoint);
}
