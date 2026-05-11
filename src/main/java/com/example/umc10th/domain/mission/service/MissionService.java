package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final UserMissionRepository userMissionRepository;

    // 내 미션 조회 (진행중 / 완료 / 전체)
    public MissionResDTO.MissionList getMyMissions(Long userId, int page, int size, String status) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Boolean isComplete = null;

        if ("COMPLETE".equalsIgnoreCase(status)) {
            isComplete = true;
        } else if ("PROGRESS".equalsIgnoreCase(status)) {
            isComplete = false;
        }

        Page<UserMission> missionPage =
                userMissionRepository.findMyMissions(userId, isComplete, pageable);

        var missionItems = missionPage.stream()
                .map(um -> MissionResDTO.MissionItem.builder()
                        .missionId(um.getMission().getId())
                        .title(um.getMission().getRestaurant().getName()) // 가게 이름
                        .content(um.getMission().getContent())
                        .reward(um.getMission().getPoint())
                        .status(um.getIsComplete() ? "COMPLETE" : "PROGRESS")
                        .build()
                )
                .toList();

        return MissionResDTO.MissionList.builder()
                .missions(missionItems)
                .page(page)
                .size(size)
                .totalPages(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .build();
    }

    // 미션 상세 조회
    public MissionResDTO.MissionItem getMissionDetail(Long userId, Long missionId) {

        UserMission userMission = userMissionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션 없음"));

        return MissionResDTO.MissionItem.builder()
                .missionId(userMission.getMission().getId())
                .title(userMission.getMission().getRestaurant().getName())
                .content(userMission.getMission().getContent())
                .reward(userMission.getMission().getPoint())
                .status(userMission.getIsComplete() ? "COMPLETE" : "PROGRESS")
                .build();
    }
}