package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 회원 미션 조회
    Page<MemberMission> findByMemberIdAndIsComplete(Long memberId, Boolean isComplete, Pageable pageable);
    Page<MemberMission> findByMemberId(Long memberId, Pageable pageable);

    // 회원 미션 개수
    long countByMemberIdAndIsComplete(Long memberId, Boolean isComplete);
    long countByMemberId(Long memberId);

    // 특정 지역에 해당하는 미션
    @Query("SELECT mm FROM MemberMission mm " +
           "JOIN mm.mission m " +
           "JOIN m.store s " +
           "JOIN s.location l " +
           "WHERE mm.member.id = :memberId " +
           "AND mm.isComplete = false " +
           "AND l.name = :address")
    Page<MemberMission> findOngoingByMemberAndAddress(
            @Param("memberId") Long memberId,
            @Param("address") Address address,
            Pageable pageable
    );
}
