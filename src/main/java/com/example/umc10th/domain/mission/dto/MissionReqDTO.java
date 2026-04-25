package com.example.umc10th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class MissionReqDTO {

    // 미션 성공 누르기
    public static class CompleteDTO {
        @Schema(example = "1234")
        private Long storeManagerId;
    }
}