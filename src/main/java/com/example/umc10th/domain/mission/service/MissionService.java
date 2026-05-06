package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionList getMissions(Long memberId, String status) {
        return null;
    }

    public String complete(Long missionId, MissionReqDTO.missionComplete request) {
        return null;
    }

    //홈화면 - 도전 가능한 미션 목록
    public MissionResDTO.MissionPreViewListDTO getAvailableMissions(
            Long memberId, Address address, Integer page) {
        return MissionConverter.toMissionPreViewListDTO(
                missionRepository.findAvailableMissions(
                        address, memberId, PageRequest.of(page, 10))
        );
    }

    //미션화면 - 내 미션 목록 (진행중/완료)
    public MissionResDTO.MyMissionListDTO getMyMissions(
            Long memberId, MissionStatus status, Integer page) {
        return MissionConverter.toMyMissionListDTO(
                memberMissionRepository.findByMemberIdAndStatus(
                        memberId, status, PageRequest.of(page, 10))
        );
    }
}