package com.example.umc10th.domain.auth.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode{

    OK(HttpStatus.OK, "COMMON200_1", "성공적으로 유저를 조회했습니다."),
    SIGNUP_SUCCESS(HttpStatus.CREATED, "COMMON200_2", "회원가입에 성공했습니다."),
    ONBOARDING_SUCCESS(HttpStatus.OK, "MEMBER200_2", "온보딩에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
