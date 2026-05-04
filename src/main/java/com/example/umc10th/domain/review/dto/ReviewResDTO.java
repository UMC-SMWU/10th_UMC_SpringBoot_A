package com.example.umc10th.domain.review.dto;

public class ReviewResDTO {

    public record CreateReviewResult(
            Long reviewId,
            Long storeId,
            Integer rating,
            String content
    ) {}

}
