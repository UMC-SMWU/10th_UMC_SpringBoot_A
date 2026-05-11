package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {

    public record GetMyMissions(
            Long userId,
            Integer page,
            Integer size,
            String status
    ) {}

}