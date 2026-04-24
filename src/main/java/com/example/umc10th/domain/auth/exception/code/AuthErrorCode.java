package com.example.umc10th.domain.auth.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements BaseErrorCode{

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404_1", "유저를 찾을 수 없습니다."),
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER400_2", "존재하지 않는 약관입니다."),
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER400_3", "존재하지 않는 음식 카테고리입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
