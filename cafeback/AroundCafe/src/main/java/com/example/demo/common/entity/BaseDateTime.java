package com.example.demo.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// Entity의 공통 분모를 빼서 부모클래스로 만드는 어노테이션
@MappedSuperclass
// 엔티티에 이벤트가 발생할 때 콜백을 처리하고 AuditingEntityListener 클래스 코드를 실행
@EntityListeners(AuditingEntityListener.class)
public class BaseDateTime {

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime updatedTime;
}
