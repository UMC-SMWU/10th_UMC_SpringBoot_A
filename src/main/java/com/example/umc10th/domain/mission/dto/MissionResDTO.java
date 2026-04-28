package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

// 서버 -> 클라이언트
public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionList {
        private List<MissionItem> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionItem {

        private Long missionId;
        private String status;
        private String title;
    }
}