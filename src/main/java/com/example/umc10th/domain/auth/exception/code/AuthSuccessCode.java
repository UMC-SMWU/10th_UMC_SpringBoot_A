package com.example.umc10th.domain.auth.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode {

    SIGNUP_SUCCESS(HttpStatus.OK, "AUTH2001", "회원가입 성공"),
    LOGIN_SUCCESS(HttpStatus.OK, "AUTH2002", "로그인 성공");


    private final HttpStatus status;
    private final String code;
    private final String message;
}