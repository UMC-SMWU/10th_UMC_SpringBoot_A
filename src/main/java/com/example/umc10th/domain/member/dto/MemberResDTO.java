package com.example.umc10th.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberResDTO {
    // 로그인 응답
    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginDTO {
        @Schema(example = "im3zero@email.com")
        private String email;
        @Schema(example = "password")
        private String password;

    }
    // 로그인 응답
    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginResultDTO {
        @Schema(example = "im3zero@email.com")
        private String email;
        @Schema(example = "kkk")
        private String token;  // 나중에 JWT 토큰 들어갈 자리
    }

    // 회원가입 응답
    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignUpResultDTO {
        @Schema(example = "im3zero")
        private Long memberId;
        @Schema(example = "양세영")
        private String name;
        @Schema(example = "im3zero@email.com")
        private String email;
        @Schema(example = "reviews")
        private List<String> reviews;

    }

    // 홈화면 응답
    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeDTO {
        @Schema(example = "1L")
        private Long memberId;
        @Schema(example = "양세영")
        private String name;
        @Schema(example = "im3zero@email.com")
        private String email;
        @Schema(example = "reviews")
        private List<String> reviews;
    }
}
