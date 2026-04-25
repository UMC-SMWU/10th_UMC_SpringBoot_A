package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionListDTO {
        private List<MissionItemDTO> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionItemDTO {
        private Long missionId;
        private String status;
        private String title;
    }
}