package com.example.demo.qNa.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.qNa.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Long> {

    @Query("select q from QnA q where q.memberInfo = :member order by q.qna_no desc")
    public List<QnA> findByMemberInfo(@Param("member") Member member);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from qna where qna_no =:qnaNo", nativeQuery = true)
    public void deleteAllById(@Param("qnaNo") Long qnaNo);

    @Query("select q from QnA q where q.received_no =:memNo order by q.qna_no desc")
    public List<QnA> findByReceived_no(Long memNo);

    @Query("select q from QnA q where q.received_no =:adminNo and q.memberInfo =:mem order by q.qna_no desc")
    List<QnA> findByReceived_noByAdmin(Member mem, Long adminNo);

    @Query("select q from QnA q where q.received_no = :membNo order by q.qna_no desc")
    List<QnA> findByMemberInfoByAdmin(Long membNo);
}
