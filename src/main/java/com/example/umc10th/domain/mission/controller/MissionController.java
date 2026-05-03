package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @GetMapping("/me")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status
    ) {

        String missionStatus = (status != null) ? status : "ALL";

        // 일단 가짜 데이터 넣기
        List<MissionResDTO.MissionItem> missions = List.of(
                MissionResDTO.MissionItem.builder()
                        .missionId(1L)
                        .title("탕화쿵푸")
                        .content("탕화쿵푸 5회 이상 방문 시 포인트 500점")
                        .reward(500)
                        .status(missionStatus)
                        .build()
        );

        MissionResDTO.MissionList response =
                MissionResDTO.MissionList.builder()
                        .missions(missions)
                        .page(page)
                        .size(size)
                        .totalPages(1)
                        .totalElements(1)
                        .build();

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_SUCCESS,
                response
        );
    }

    @GetMapping("/me/{missionId}")
    public ApiResponse<MissionResDTO.MissionItem> getMissionDetail(
            @PathVariable Long missionId
    ) {

        // 일단 가짜 데이터 넣기
        MissionResDTO.MissionItem mission =
                MissionResDTO.MissionItem.builder()
                        .missionId(missionId)
                        .title("탕화쿵푸")
                        .content("탕화쿵푸 5회 이상 방문 시 포인트 500점")
                        .reward(500)
                        .status("COMPLETE")
                        .build();

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_SUCCESS,
                mission
        );
    }
}