package com.example.umc10th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(example = "미션1")
        private Long missionId;


        @Schema(example = "진행중")
        private String status;

        @Schema(example = "사진찍기")
        private String title;
    }
}