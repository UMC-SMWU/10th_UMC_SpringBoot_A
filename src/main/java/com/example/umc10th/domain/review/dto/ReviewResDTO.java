package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewResult(
            Long reviewId,
            String rate,
            String content,
            List<String> imageUrls            //Integer point
    ) {
    }
}