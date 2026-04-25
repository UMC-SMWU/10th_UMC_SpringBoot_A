package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    public MissionResDTO.MissionListDTO getMissions(Long userId, String status) {
        //실제 로직 구현
        return null;
    }

    public String complete(Long missionId, MissionReqDTO.CompleteDTO request) {
        //실제 로직 구현
        return null;
    }
}