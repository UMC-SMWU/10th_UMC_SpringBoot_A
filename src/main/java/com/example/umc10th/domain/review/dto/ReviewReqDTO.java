package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReview(
            Long restaurantId,
            String title,
            int star,
            String date,
            String content
    ) {}

}