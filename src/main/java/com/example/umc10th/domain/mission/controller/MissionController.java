package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.ApiResponse;
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
    public ResponseEntity<ApiResponse<MissionResDTO.MissionListDTO>> getMissions(
            @PathVariable Long userId,
            @RequestParam String status) {
        MissionResDTO.MissionListDTO result = MissionResDTO.MissionListDTO.builder()
                .missions(List.of(
                        MissionResDTO.MissionItemDTO.builder()
                                .missionId(1L)
                                .status(status)
                                .title("첫 번째 미션")
                                .build()
                ))
                .build();
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{missionId}/complete")
    public ResponseEntity<ApiResponse<String>> completeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.CompleteDTO request) {
        return ResponseEntity.ok(ApiResponse.onSuccess("미션 성공 처리 완료"));
    }
}