package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import java.util.List;
import lombok.Builder;

public class MissionResDTO {

//    @Builder
//    public record MyMissionList(
//            List<MyMission> missions
//    ) {}

    @Builder
    public record MyMission(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String missionContent,
            Integer reward,
            MissionCompleteStatus status,
            String storeVerificationCode
    ) {}

    @Builder
    public record MyMissionList(
            List<MyMission> missions,
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            Boolean hasNext
    ) {}

    public record MissionCompleteResult(
            Long userMissionId,
            MissionCompleteStatus status
    ) {}

    //가게 내 미션 조회
    @Builder
    public record GetStoreMission(
            Long missionId,
            Integer reward,
            String missionContent
    ){}

    //페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}
}
