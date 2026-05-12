package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping("/users/{userId}/missions")
    public ApiResponse<MissionResDTO.OffsetPagination<MissionResDTO.MissionInfo>> getMissionList(
            @PathVariable Long userId,
            @RequestParam(required = false) String status,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, missionService.getMissionList(userId, status, pageSize, pageNumber, sort));
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{memberMissionId}")
    public ApiResponse<MissionResDTO.MissionSuccessInfo> completeMission(
            @PathVariable Long memberMissionId,
            @RequestBody @Valid MissionReqDTO.CompleteStatus dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_SUCCESS_OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(memberMissionId, dto));
    }

    // 가게 미션 목록 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_GET_OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, cursor, query));
    }

    // 가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_CREATE_OK;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }
}
