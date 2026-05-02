package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
public class MissionController {

    // 미션 목록 조회 (진행 중, 완료)
    @GetMapping("/api/users/{userId}/missions")
    public ApiResponse<MissionResDTO.MissionListInfo> getMissionList(
            @PathVariable Long userId,
            @RequestParam String status
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 미션 성공 누르기
    @PatchMapping("/api/users/{userId}/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionSuccessInfo> completeMission(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.CompleteStatus dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_SUCCESS_OK;
        return ApiResponse.onSuccess(code, null);
    }
}