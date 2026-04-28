package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_COMPLETE(HttpStatus.OK, "MISSION_2001", "미션 성공!");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
