package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberReqDTO {

    // 회원가입
    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignUpDTO {
        private String name;
        private String gender;
        private String birthDate;
        private String address;
    }

    // 로그인
    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
