package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.MemberRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

    // 특정 MemberRole을 갖는 Member들을 찾기 위하여 사용
    Optional<MemberRole> findByName(MemberRoleType name);
    List<MemberRole> findAllByName(MemberRoleType name);
}
