package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        ReviewResDTO.CreateReview response =
                ReviewResDTO.CreateReview.builder()
                        .reviewId(1L)
                        .title(request.title())
                        .star(request.star())
                        .content(request.content())
                        .build();
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_CREATED,
                response
        );
    }
}