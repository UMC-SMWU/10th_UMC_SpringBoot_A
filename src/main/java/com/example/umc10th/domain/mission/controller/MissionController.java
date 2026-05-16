package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/me")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
            @RequestBody MissionReqDTO.GetMyMissions request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getMyMissions(
                        request.userId(),
                        request.page(),
                        request.size(),
                        request.status()
                )
        );
    }

    @GetMapping("/me/{missionId}")
    public ApiResponse<MissionResDTO.MissionItem> getMissionDetail(
            @RequestParam Long userId,
            @PathVariable Long missionId
    ) {

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getMissionDetail(userId, missionId)
        );
    }
}