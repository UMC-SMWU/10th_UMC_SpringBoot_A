package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    // 미션 목록 조회
    public MissionResDTO.MissionListInfo getMissionList(Long userId, String status, Pageable pageable) {
        Page<MemberMission> missionPage;
        if ("complete".equalsIgnoreCase(status)) {
            missionPage = memberMissionRepository.findByMemberIdAndIsComplete(userId, true, pageable);
        } else if ("ongoing".equalsIgnoreCase(status)) {
            missionPage = memberMissionRepository.findByMemberIdAndIsComplete(userId, false, pageable);
        } else { // 전체 미션 조회
            missionPage = memberMissionRepository.findByMemberId(userId, pageable);
        }
        return MissionConverter.toMissionListInfo(missionPage);
    }

    // 미션 완료 처리
    @Transactional
    public MissionResDTO.MissionSuccessInfo completeMission(Long memberMissionId, MissionReqDTO.CompleteStatus dto) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        memberMission.updateComplete(dto.isComplete());

        return MissionConverter.toMissionSuccessInfo(memberMission);
    }
}
