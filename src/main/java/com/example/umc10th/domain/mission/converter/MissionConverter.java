package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import java.util.List;
import org.springframework.data.domain.Page;

public class MissionConverter {

    //가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .missionContent(dto.missionContent())
                .reward(dto.reward())
                .deadline(dto.deadline())
                .build();
    }

    public static MissionResDTO.MyMission toOngoingMission(MemberMission userMission) {
        Mission mission = userMission.getMission();

        return MissionResDTO.MyMission.builder()
                .memberMissionId(userMission.getId())
                .missionId(mission.getId())
                .storeName(userMission.getStore().getStoreName())
                .missionContent(mission.getMissionContent())
                .reward(mission.getReward())
                .status(userMission.getIsCompleted())
                .storeVerificationCode(mission.getVerificationCode())
                .build();
    }

    // 진행중인 미션 리스트 변환(Page)
    public static MissionResDTO.MyMissionList toMyMissionList(
            Page<MemberMission> memberMissionPage
    ) {

        List<MissionResDTO.MyMission> missionList = memberMissionPage.getContent()
                .stream()
                .map(MissionConverter::toOngoingMission)
                .toList();

        return MissionResDTO.MyMissionList.builder()
                .missions(missionList)
                .page(memberMissionPage.getNumber())
                .size(memberMissionPage.getSize())
                .totalElements(memberMissionPage.getTotalElements())
                .totalPages(memberMissionPage.getTotalPages())
                .hasNext(memberMissionPage.hasNext())
                .build();
    }

    //미션 리스트 변환(List)
    public static MissionResDTO.MyMissionList toMyMissionList(
            List<MemberMission> memberMissions
    ) {
        List<MissionResDTO.MyMission> missions = memberMissions.stream()
                .map(MissionConverter::toOngoingMission)
                .toList();

        return MissionResDTO.MyMissionList.builder()
                .missions(missions)
                .build();
    }

    //가게 내 미션 조회
    public static MissionResDTO.GetStoreMission toGetStoreNission(
            Mission mission
    ){
        return MissionResDTO.GetStoreMission.builder()
                .missionContent(mission.getMissionContent())
                .reward(mission.getReward())
                .missionId(mission.getId())
                .build();
    }

    //페이지네이션 틀
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
