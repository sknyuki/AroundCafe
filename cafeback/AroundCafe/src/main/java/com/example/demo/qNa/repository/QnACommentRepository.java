package com.example.demo.qNa.repository;

import com.example.demo.qNa.entity.QnAComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnACommentRepository extends JpaRepository<QnAComment, Long> {
}
