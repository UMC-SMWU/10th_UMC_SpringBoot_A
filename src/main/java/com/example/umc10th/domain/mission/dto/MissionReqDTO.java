package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {
    public record missionComplete(Long memberId, String password, Long storeManagerId){
    }

}