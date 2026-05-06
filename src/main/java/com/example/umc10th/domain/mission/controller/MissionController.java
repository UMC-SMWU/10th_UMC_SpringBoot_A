package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO.MyMissionList;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.exception.code.StoreSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;

    //내 미션 조회
    @GetMapping("/missions/my/{memberId}")
    public ApiResponse<MyMissionList> getMyMissions(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MY_MISSION_LIST_SUCCESS,
                missionService.getMyMissions(memberId));
    }

    //진행중인 미션들 조회
    @PostMapping("/missions/my/ongoing")
    public ApiResponse<MyMissionList> getMyOngoingMissions(
            @RequestBody @Valid MissionReqDTO.MyMissionRequest request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MY_MISSION_LIST_SUCCESS,
                missionService.getMyOngoingMissions(request)
        );
    }

    //미션 성공 요청
    @PostMapping("/user-missions/{memberMissionId}/complete")
    public ApiResponse<MissionResDTO.MissionCompleteResult> completeMission(
            @PathVariable Long memberMissionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS,
                missionService.completeMission(memberMissionId));
    }

    //가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.CreateMission dto
    ){
        return ApiResponse.onSuccess(StoreSuccessCode.MISSION_CREATED,
                missionService.createMission(storeId, dto));
    }

    //가게 내 미션들 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetStoreMission>> getStoreMission(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam (required = false) String sort
    ){
        return ApiResponse.onSuccess(StoreSuccessCode.STORE_MISSION_LIST_SUCCESS,
                missionService.getStoreMissions(storeId, pageSize, pageNumber, sort));
    }
}
