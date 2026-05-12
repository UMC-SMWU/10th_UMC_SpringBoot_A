package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO.MyMission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional
public class MissionService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 내 미션 조회
    @Transactional(readOnly = true)
    public MissionResDTO.MyMissionList getMyMissions(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        List<MyMission> missions = memberMissionRepository.findByMember(member)
                .stream()
                .map(memberMission -> new MissionResDTO.MyMission(
                        memberMission.getId(),
                        memberMission.getMission().getId(),
                        memberMission.getStore().getStoreName(),
                        memberMission.getMission().getMissionContent(),
                        memberMission.getMission().getReward(),
                        memberMission.getIsCompleted(),
                        memberMission.getMission().getVerificationCode()
                ))
                .toList();

        return new MissionResDTO.MyMissionList(missions);
    }

    // 미션 성공 요청
    public MissionResDTO.MissionCompleteResult completeMission(Long memberMissionId) {

        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원 미션입니다."));

        if (memberMission.getIsCompleted() == MissionCompleteStatus.COMPLETED) {
            throw new RuntimeException("이미 완료된 미션입니다.");
        }

        memberMission.complete();

        return new MissionResDTO.MissionCompleteResult(
                memberMission.getId(),
                memberMission.getIsCompleted()
        );
    }
}
