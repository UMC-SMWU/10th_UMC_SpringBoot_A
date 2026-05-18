package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 추가: 이메일로 회원 조회 (로그인 & 중복 체크)
    Optional<Member> findByEmail(String email);
}