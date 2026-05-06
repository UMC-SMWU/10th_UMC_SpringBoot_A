package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/users/{userId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewInfo> writeReview(
            @PathVariable Long userId,
            @RequestBody ReviewReqDTO.WriteReview dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_WRITE_OK;
        return ApiResponse.onSuccess(code, reviewService.writeReview(userId, dto));
    }
}
