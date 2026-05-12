package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    // 회원 미션 없음
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW400_1", "존재하지 않는 회원 미션입니다."),
    // 미션 완료 안 했는데 리뷰 쓰려는 경우
    REVIEW_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "REVIEW400_2", "완료된 미션만 리뷰 작성이 가능합니다."),
    // 평점 범위 오류
    INVALID_RATING(HttpStatus.BAD_REQUEST, "REVIEW400_3", "평점은 1~5 사이여야 합니다."),
    // 내용 없음
    EMPTY_REVIEW_CONTENT(HttpStatus.BAD_REQUEST, "REVIEW400_4", "리뷰 내용은 필수입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
