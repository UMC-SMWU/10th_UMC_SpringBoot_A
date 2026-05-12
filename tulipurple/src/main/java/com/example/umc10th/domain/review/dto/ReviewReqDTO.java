package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    // 리뷰 작성
    public record WriteReview(
            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,
            @NotBlank(message = "내용은 빈칸일 수 없습니다.")
            String content,
            @NotNull(message = "0-5 별점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "별점 범위는 0-5입니다.")
            @DecimalMax(value = "5.0", message = "별점 범위는 0-5입니다.")
            Float star
    ){}
}