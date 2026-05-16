package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @Valid @RequestBody ReviewReqDTO.CreateReview request
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

    @GetMapping("/me")
    public ApiResponse<ReviewResDTO.ReviewList> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_LIST_SUCCESS, reviewService.getMyReviews(userId, cursor, size, sort)
        );
    }
}