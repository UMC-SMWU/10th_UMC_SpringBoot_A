package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        SELECT um FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.restaurant
        WHERE um.user.id = :userId
        AND (:isComplete IS NULL OR um.isComplete = :isComplete)
        ORDER BY m.createdAt DESC
    """)
    Page<UserMission> findMyMissions(
            @Param("userId") Long userId,
            @Param("isComplete") Boolean isComplete,
            Pageable pageable
    );
}