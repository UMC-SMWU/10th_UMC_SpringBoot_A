package com.example.umc10th.global.apiPayload;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();
}