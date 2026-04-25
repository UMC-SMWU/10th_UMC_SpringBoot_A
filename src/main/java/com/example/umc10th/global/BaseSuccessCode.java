package com.example.umc10th.global;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}