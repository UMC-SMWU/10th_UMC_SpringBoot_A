package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ReviewResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateResultDTO {
        @Schema(example = "reviewId")
        private Long reviewId;
//
        @Schema(example = "⭐⭐⭐⭐⭐️")
        private String rate;

        @Schema(example = "content")
        private String content;

        @Schema(example = "imageUrls")
        private List<String> imageUrls;
    }
}