package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

// 서버 -> 클라이언트
public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionList {
        private List<MissionItem> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionItem {

        private Long missionId;
        private String status;
        private String title;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        private String storeName;
        private Integer reward;
        private String missionSpec;
        private LocalDate deadline;
    }

    // ⭐ 홈화면 - 미션 목록 페이징
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        private List<MissionPreViewDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    // ⭐ 미션화면 - 내 미션
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionDTO {
        private String storeName;
        private Integer reward;
        private String missionSpec;
        private MissionStatus status;
    }

    // ⭐ 미션화면 - 내 미션 목록 페이징
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionListDTO {
        private List<MyMissionDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}