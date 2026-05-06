package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionInfo toMissionInfo(MemberMission mm) {
        return MissionResDTO.MissionInfo.builder()
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .conditional(mm.getMission().getConditional())
                .point(mm.getMission().getPoint())
                .deadline(mm.getMission().getDeadline())
                .isComplete(mm.getIsComplete())
                .build();
    }

    public static MissionResDTO.MissionListInfo toMissionListInfo(Page<MemberMission> missionPage) {
        List<MissionResDTO.MissionInfo> missionList = missionPage.stream()
                .map(MissionConverter::toMissionInfo)
                .toList();
        return MissionResDTO.MissionListInfo.builder()
                .missionList(missionList)
                .totalPage(missionPage.getTotalPages())
                .build();
    }

    public static MissionResDTO.MissionSuccessInfo toMissionSuccessInfo(MemberMission mm) {
        return MissionResDTO.MissionSuccessInfo.builder()
                .memberMissionId(mm.getId())
                .build();
    }
}
