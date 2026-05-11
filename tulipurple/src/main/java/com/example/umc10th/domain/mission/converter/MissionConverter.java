package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
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

    // 가게 미션 생성
    public static Mission toMission(
        Store store,
        MissionReqDTO.CreateMission dto
    ){
      return Mission.builder()
              .store(store)
              .conditional(dto.conditional())
              .point(dto.point())
              .deadline(dto.deadLine())
              .build();
    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    //오프셋 페이지네이션 툴 생성
    public static <T> MissionResDTO.OffsetPagination<T> toOffsetPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.OffsetPagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    //페이지네이션 툴 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
