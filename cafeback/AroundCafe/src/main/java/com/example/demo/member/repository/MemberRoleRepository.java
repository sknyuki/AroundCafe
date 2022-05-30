package com.example.demo.member.repository;

import com.example.demo.member.entity.MemberRole;
import com.example.demo.member.entity.MemberRoleType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRoleRepository {

    Optional<MemberRole> findByName(MemberRoleType name);
}
