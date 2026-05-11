package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
// 서버 -> 클라이언트
public class MemberResDTO {
    // 회원가입
    @Builder
    public record SignUp(
            String name,
            Long memberId,
            String gender,
            String email,
            String phoneNumber,
            String birthday_Date
    ) {
    }


    //로그인
    @Builder
    public record Login(

            Long memberId,
            String email,
            String password
    ) {
    }

    // 홈화면
    @Builder
    public record HomeInfo(
            Long memberId,
            String name,
            String email,
            String phoneNumber,
            Integer point,
            List<String> reviews
    ) {
    }

    @Builder
    public record MyPageDTO(
            String nickname,
            String email,
            Integer point
    ) {}

    // 미션1: 진행중인 미션 목록 (오프셋 페이지네이션)
    @Builder
    public record MissionPageResponse(
            List<MissionInfo> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionInfo(
            Long missionId,
            String storeName,
            Integer point,
            String conditional,
            LocalDate deadline,
            String status
    ) {}

    // 미션2: 내가 작성한 리뷰 목록 (커서 페이지네이션)
    @Builder
    public record ReviewPageResponse(
            List<ReviewInfo> reviewList,
            Integer listSize,
            Boolean hasNext,
            String nextCursor
    ) {}

    @Builder
    public record ReviewInfo(
            Long reviewId,
            String storeName,
            Float star,
            String body,
            LocalDate createdAt
    ) {}

}

