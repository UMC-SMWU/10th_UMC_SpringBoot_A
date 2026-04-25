package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    // 회원가입 응답
    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignUpResultDTO {
        private Long memberId;
        private String name;
    }

    // 홈화면 응답
    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeDTO {
        private Long memberId;
        private String name;
    }
    }
