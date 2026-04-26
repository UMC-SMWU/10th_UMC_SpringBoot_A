package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "해당 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_IN_PROGRESS(HttpStatus.BAD_REQUEST, "MISSION4002", "이미 진행 중인 미션입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION4003", "이미 완료된 미션입니다."),
    MISSION_FORBIDDEN(HttpStatus.FORBIDDEN, "MISSION4004", "해당 미션에 접근할 권한이 없습니다."),
    MISSION_PAGE_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "MISSION4005", "요청한 페이지가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}