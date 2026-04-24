package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO.MyMissionList;
import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
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
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    //내 미션 조회
    @GetMapping("/my/{memberId}")
    public ApiResponse<MyMissionList> getMyMissions(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MY_MISSION_LIST_SUCCESS,
                missionService.getMyMissions(memberId));
    }

    //미션 성공 요청
    @PostMapping("/user-missions/{memberMissionId}/complete")
    public ApiResponse<MissionResDTO.MissionCompleteResult> completeMission(
            @PathVariable Long memberMissionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS,
                missionService.completeMission(memberMissionId));
    }

}
