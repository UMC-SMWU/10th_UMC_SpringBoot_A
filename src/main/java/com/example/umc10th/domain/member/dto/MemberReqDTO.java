package com.example.umc10th.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberReqDTO {

    // 회원가입
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpDTO {
        @Schema(example = "세영")
        private String name;

        @Schema(example = "FEMALE")
        private String gender;

        @Schema(example = "2001-08-15")
        private String birthDate;

        @Schema(example = "서울시 강서구")
        private String address;
    }

    // 로그인
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginDTO {
        @Schema(example = "im3zero@email.com")
        private String email;
        @Schema(example = "password")
        private String password;
    }
}
