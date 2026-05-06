package com.example.umc10th.domain.member.dto;

import lombok.Builder;

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

}

