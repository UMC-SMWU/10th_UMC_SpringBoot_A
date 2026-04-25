package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGN_UP_SUCCESS(HttpStatus.CREATED, "MEMBER_2001", "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "MEMBER_2002", "로그인이 완료되었습니다."),
    HOME_SUCCESS(HttpStatus.OK, "MEMBER_2003", "홈 화면 조회 성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}