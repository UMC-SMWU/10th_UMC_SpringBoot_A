package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping("/users/{userId}/missions")
    public ApiResponse<MissionResDTO.MissionListInfo> getMissionList(
            @PathVariable Long userId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, missionService.getMissionList(userId, status, PageRequest.of(page, size)));
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{memberMissionId}")
    public ApiResponse<MissionResDTO.MissionSuccessInfo> completeMission(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.CompleteStatus dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_SUCCESS_OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(memberMissionId, dto));
    }
}