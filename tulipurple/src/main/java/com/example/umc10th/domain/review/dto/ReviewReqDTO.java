package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    // 리뷰 작성
    public record WriteReview(
            Long storeId,
            String content,
            Float star
    ){}
}