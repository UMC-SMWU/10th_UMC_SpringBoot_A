package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO.MyMissionList;
import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    //내 미션 조회
    @GetMapping("/my")
    public ApiResponse<MyMissionList> getMyMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam MissionCompleteStatus status,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(status, page, size));
    }

    //미션 성공 요청
    @PostMapping("/user-missions/{userMissionId}/complete")
    public ApiResponse<MissionResDTO.CompleteMissionResult> completeMission(
            @PathVariable Long userMissionId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody MissionReqDTO.CompleteMission dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(userMissionId, dto));
    }

}
