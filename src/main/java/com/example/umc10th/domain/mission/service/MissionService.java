package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    public MissionResDTO.MissionList getMissions(Long memberId, String status) {
        return null;
    }

    public String complete(Long missionId, MissionReqDTO.missionComplete request) {
        return null;
    }
}