package com.example.umc10th.domain.review.dto;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

public class ReviewResDTO {

    public record CreateReviewResult(
            Long reviewId,
            Long storeId,
            Integer rating,
            String content
    ) {}

    @Builder
    public record MyReviewList(
            List<MyReview> reviews,
            Boolean hasNext,
            Long nextCursorId,
            Integer nextCursorStar
    ) {}

    @Builder
    public record MyReview(
            Long reviewId,
            String storeName,
            Integer star,
            String reviewContent,
            String reply
    ) {}
}
