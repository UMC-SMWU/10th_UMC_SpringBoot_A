package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회 (진행 중, 완료)
    @GetMapping("/users/{userId}/missions")
    public ApiResponse<MissionResDTO.MissionListInfo> getMissionList(
            @PathVariable Long userId,
            @RequestParam String status
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, missionService.getMissionList(userId, status));
    }

    // 미션 성공 누르기
    @PatchMapping("/users/{userId}/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionSuccessInfo> completeMission(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.CompleteStatus dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_SUCCESS_OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(missionId, dto));
    }
}