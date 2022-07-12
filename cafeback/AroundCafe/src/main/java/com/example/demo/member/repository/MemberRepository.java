package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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

    @Query("select m from Member m join fetch m.cafe c where c.cafeNo = :received_no")
    Optional<Member> findByIdFromCafeNo(@Param("received_no") Long received_no);
    @Query("select m from Member m where m.memBirth like %:nowDay% ")
    List<Member> findByMemBirthLike(@Param("nowDay") String nowDay);

    @Query("select m from Member m join fetch m.payment p where m.memNo = :memNo")
    Optional<Member> findMemberByMemNoWithPayment(@Param("memNo") Long memNo);

    @Query("select m from Member m join fetch m.role r where r.name = 'ROLE_ADMIN'")
    Optional<Member> findByAdmin();

    //@Query(value = "select * from members where mem_no in (select id from member_role where name = 'ROLE_ADMIN')",nativeQuery = true)
}
