package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_4001", "존재하지 않는 유저입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER_4002", "이미 사용 중인 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER_4003", "비밀번호가 올바르지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}