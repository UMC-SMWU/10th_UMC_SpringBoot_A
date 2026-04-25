package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회 (ONGOING / COMPLETED)
    @GetMapping("/{userId}/missions")
    public ResponseEntity<MissionResDTO.MissionListDTO> getMissions(
            @PathVariable Long userId,
            @RequestParam String status,   // "ONGOING" or "COMPLETED"
            @RequestHeader("Authorization") String accessToken) {
        // TODO: missionService.getMissions(userId, status) 연결
        return ResponseEntity.ok(null);
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{missionId}/complete")
    public ResponseEntity<String> completeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.CompleteDTO request,
            @RequestHeader("Authorization") String accessToken) {
        // TODO: missionService.complete(missionId, request) 연결
        return ResponseEntity.ok("미션 성공 처리 완료");
    }
}