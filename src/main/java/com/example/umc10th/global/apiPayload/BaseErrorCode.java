package com.example.umc10th.global.apiPayload;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    //INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON5001", "서버 에러가 발생했습니다.");

    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}