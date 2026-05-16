package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateReview(
            @NotNull(message = "가게 ID는 필수입니다.")
            Long restaurantId,

            @NotBlank(message = "리뷰 제목은 필수입니다.")
            String title,

            @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
            @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
            int star,

            @NotBlank(message = "날짜는 필수입니다.")
            String date,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content
    ) {}

}