package com.example.demo.qNa.repository;

import com.example.demo.qNa.entity.QnA;
import com.example.demo.qNa.entity.QnAComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface QnACommentRepository extends JpaRepository<QnAComment, Long> {

    @Transactional
    @Query(value = "select * from qna_comment where qna_no = :qnaNo order by qna_comment_no asc",nativeQuery = true)
    public List<QnAComment> findByQnA(@Param("qnaNo")Integer qnaNo);

    @Transactional
    @Query(value = "select * from qna_comment where qna_no =:qnaNo order by qna_comment_no desc",nativeQuery = true)
    List<QnAComment> findByImg(@Param("qnaNo")Integer qnaNo);
}
