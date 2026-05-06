package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    MISSION_CREATED(HttpStatus.OK, "MISSION200_3", "미션 생성에 성공했습니다."),
    STORE_MISSION_LIST_SUCCESS(HttpStatus.OK, "MISSION200_4", "가게 미션 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}