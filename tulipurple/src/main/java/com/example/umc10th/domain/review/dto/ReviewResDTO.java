package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    // 리뷰 작성
    @Builder
    public record ReviewInfo(
            Long reviewId
    ){}

    // 내 리뷰 조회
    @Builder
    public record GetReview(
            Long reviewId,
            String storeName,
            String content,
            Float star
    ){}
}