package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.OK, "REVIEW200_1", "리뷰 생성 성공"),
    REVIEW_LIST_SUCCESS(HttpStatus.OK, "REVIEW200_2", "내 리뷰 목록 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}