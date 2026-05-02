package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    // 마이페이지
    @Builder
    public record GetInfo(
            String name,
            String profileImgUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    // 홈화면
    @Builder
    public record HomeInfo(
            String address,
            Integer point,
            Integer missionSuccessCount,
            Integer missionGoalCount,
            Integer missionRewardPoint
    ){}

    // 회원가입
    @Builder
    public record SignUpInfo(
            Long memberId
    ){}
}