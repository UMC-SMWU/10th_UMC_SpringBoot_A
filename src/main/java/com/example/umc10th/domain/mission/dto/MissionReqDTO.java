package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class MissionReqDTO {

    public record CompleteMission(
            String storeVerificationCode
    ) {}

    //가게 미션 생성
    public record CreateMission(
            LocalDate deadline,
            Integer reward,
            String missionContent
    ){}

    public record MyMissionRequest(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,

            @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
            Integer page,

            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer size
    ) {}
}
