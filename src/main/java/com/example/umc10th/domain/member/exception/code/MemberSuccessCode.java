package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200_1", "성공적으로 유저를 조회했습니다."),
    HOME_INFO_SUCCESS(HttpStatus.OK, "MEMBER200_2", "홈 화면 조회에 성공했습니다."),
    MYPAGE_SUCCESS(HttpStatus.OK, "MEMBER200_3", "마이페이지 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
