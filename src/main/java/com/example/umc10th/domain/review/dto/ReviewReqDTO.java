package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReview(
            String rate,
            String content,
            List<String> imageUrls
    ) {
    }
}