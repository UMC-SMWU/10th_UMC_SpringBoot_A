package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    List<MemberMission> findByMember(Member member);

    Page<MemberMission> findAllByMember_IdAndIsCompleted(
            Long memberId,
            MissionCompleteStatus isCompleted,
            Pageable pageable
    );

    // 완료된 미션 개수
    Integer countByMember_IdAndIsCompleted(Member member, MissionCompleteStatus isCompleted);

    // 상태별 미션 조회
    List<MemberMission> findByMemberAndIsCompleted(Member member, MissionCompleteStatus isCompleted);
}
