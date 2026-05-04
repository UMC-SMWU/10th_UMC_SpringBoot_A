package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    public MissionResDTO.MyMissionList getMyMissions(MissionCompleteStatus status, Integer page, Integer size) {
        return null;
    }

    public MissionResDTO.CompleteMissionResult completeMission(Long userMissionId, MissionReqDTO.CompleteMission dto) {
        return null;
    }
}
