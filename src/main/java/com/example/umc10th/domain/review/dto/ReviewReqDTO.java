package com.example.umc10th.domain.review.dto;

import com.example.umc10th.domain.review.enums.ReviewSortType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ReviewReqDTO {

    public record CreateReview(
            Long memberMissionId,
            Integer rating,
            String reviewContent,
            List<String> photoUrls
    ) {}

    public record ViewMyReview(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,

            @Schema(example = "null", nullable = true)
            Long cursorId,

            @Schema(example = "null", nullable = true)
            Double cursorScore,

            @NotNull(message = "조회 개수는 필수입니다.")
            @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
            Integer size,

            @NotNull(message = "정렬 기준은 필수입니다.")
            @Schema(example = "STAR")
            ReviewSortType sortType
    ) {}
}
