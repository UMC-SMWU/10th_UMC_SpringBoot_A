package com.example.umc10th.domain.member.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

public class MemberResDTO {

    // 마이페이지
    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    // 홈화면
    @Builder
    public record HomeInfo(
            String location,
            Integer point,
            Integer missionSuccessCount,
            Integer missionTotalCount,
            List<HomeMissionInfo> missionList
    ){}

    // 홈화면 - 미션 상세정보
    @Builder
    public record HomeMissionInfo(
            Long missionId,
            String storeName,
            String conditional,
            Integer point,
            LocalDate deadline,
            Long dDay
    ){}

    // 회원가입
    @Builder
    public record SignUpInfo(
            Long memberId
    ){}
}