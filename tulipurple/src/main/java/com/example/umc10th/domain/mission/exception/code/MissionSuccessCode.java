package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_OK(HttpStatus.OK, "MISSION200_1", "성공적으로 미션 목록을 조회했습니다."),
    MISSION_SUCCESS_OK(HttpStatus.OK, "MISSION200_2", "성공적으로 미션 성공 처리되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}