package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import java.util.List;

public class MissionResDTO {

    public record MyMissionList(
            List<MyMission> missions,
            Integer page,
            Integer size,
            Integer totalPages,
            Long totalElements
    ) {}

    public record MyMission(
            Long userMissionId,
            String storeName,
            String missionContent,
            Integer reward,
            MissionCompleteStatus status,
            String storeVerificationCode
    ) {}

    public record CompleteMissionResult(
            Long userMissionId,
            MissionCompleteStatus status
    ) {}

}
