package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import java.util.List;

public class MissionResDTO {

    public record MyMissionList(
            List<MyMission> missions
    ) {}

    public record MyMission(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String missionContent,
            Integer reward,
            MissionCompleteStatus status,
            String storeVerificationCode
    ) {}

    public record MissionCompleteResult(
            Long userMissionId,
            MissionCompleteStatus status
    ) {}

}
