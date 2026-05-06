package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT m FROM Mission m
        JOIN FETCH m.restaurant r
        WHERE r.address LIKE %:region%
        ORDER BY m.createdAt DESC
    """)
    Page<Mission> findAvailableMissions(
            @Param("region") String region,
            Pageable pageable
    );

}