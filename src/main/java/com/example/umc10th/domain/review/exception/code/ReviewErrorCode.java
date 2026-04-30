package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "REVIEW400_1", "이미 리뷰가 존재합니다."),
    INVALID_REVIEW_STAR(HttpStatus.BAD_REQUEST, "REVIEW400_2", "별점은 1점에서 5점 사이여야 합니다."),
    REVIEW_CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "REVIEW400_3", "리뷰 내용을 입력해주세요."),
    REVIEW_FORBIDDEN(HttpStatus.FORBIDDEN, "REVIEW403_1", "해당 리뷰에 대한 권한이 없습니다."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}