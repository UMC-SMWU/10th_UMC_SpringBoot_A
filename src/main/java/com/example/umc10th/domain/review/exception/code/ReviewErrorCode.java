package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor

public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "리뷰를 찾을 수 없습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REVIEW4002", "이미 작성한 리뷰가 있습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}

