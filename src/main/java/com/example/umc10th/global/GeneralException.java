package com.example.umc10th.global;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public GeneralException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}