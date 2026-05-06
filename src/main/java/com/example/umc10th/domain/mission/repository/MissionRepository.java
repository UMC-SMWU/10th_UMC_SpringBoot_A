package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈화면: 특정 지역에서 내가 아직 도전 안 한 미션 목록 (페이징)
    @Query("SELECT m FROM Mission m " +
            "WHERE m.store.location.address = :address " +
            "AND m.id NOT IN (" +
            "SELECT mm.mission.id FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId" +
            ")")
    Page<Mission> findAvailableMissions(
            @Param("address") Address address,
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}