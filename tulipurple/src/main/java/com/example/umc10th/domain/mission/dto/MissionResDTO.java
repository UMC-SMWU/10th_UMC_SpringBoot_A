package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    // 미션 목록 조회
    @Builder
    public record MissionInfo(
            Long missionId,
            String storeName,
            String conditional,
            Integer point,
            LocalDate deadline,
            Boolean isComplete
    ){}

    @Builder
    public record MissionListInfo(
            List<MissionInfo> missionList,
            int totalPage
    ){}

    // 미션 성공 누르기
    @Builder
    public record MissionSuccessInfo(
            Long memberMissionId
    ){}
}