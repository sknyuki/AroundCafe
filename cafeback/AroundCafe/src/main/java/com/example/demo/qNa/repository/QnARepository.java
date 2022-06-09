package com.example.demo.qNa.repository;

import com.example.demo.qNa.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Long> {

}
