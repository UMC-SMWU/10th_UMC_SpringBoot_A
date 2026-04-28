package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGN_UP_SUCCESS(HttpStatus.CREATED, "MEMBER_101", "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "MEMBER_102", "로그인이 완료되었습니다."),
    HOME_SUCCESS(HttpStatus.OK, "MEMBER_103", "홈 화면입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}