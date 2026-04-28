package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회 (ONGOING / COMPLETED)
    @GetMapping("/{userId}/missions")
    public ApiResponse<MissionResDTO.MissionList> getMissions(
            @PathVariable Long userId,
            @RequestParam String status) {
        MissionResDTO.MissionList result = missionService.getMissions(userId, status);
        return ApiResponse.onSuccess(result);
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{missionId}/complete")
    public ApiResponse<String> completeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.missionComplete request) {
        String result = missionService.complete(missionId, request);
        return ApiResponse.onSuccess(result);
    }
}