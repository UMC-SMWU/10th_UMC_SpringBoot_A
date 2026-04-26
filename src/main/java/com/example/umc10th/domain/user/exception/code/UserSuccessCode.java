package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    USER_INFO_SUCCESS(HttpStatus.OK, "USER2001", "유저 정보 조회 성공"),
    USER_UPDATE_SUCCESS(HttpStatus.OK, "USER2002", "유저 정보 수정 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}