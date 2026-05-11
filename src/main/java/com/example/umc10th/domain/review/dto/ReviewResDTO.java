package com.example.umc10th.domain.review.dto;

import com.example.umc10th.domain.review.entity.Review;
import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            Long reviewId,
            String title,
            int star,
            String content
    ) {}

    @Builder
    public record ReviewItem(
            Long reviewId,
            String restaurantName,
            Float star,
            String content,
            String createdAt
    ) {}

    @Builder
    public record ReviewList(
            List<ReviewItem> review,
            Long nextCursor,
            boolean hasNext
    ) {}

}