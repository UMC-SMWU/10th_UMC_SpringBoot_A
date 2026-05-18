package com.example.umc10th.global.security;

import com.example.umc10th.global.apiPayload.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SecurityErrorCode implements BaseErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH4001", "로그인이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH4031", "접근 권한이 없습니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "AUTH4002", "이메일 또는 비밀번호가 올바르지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}