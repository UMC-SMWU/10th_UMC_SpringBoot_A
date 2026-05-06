package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResult> createReview(
            @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_SUCCESS, reviewService.createReview(dto));
    }

    //리뷰 조회
    @PostMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.MyReviewList> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.ViewMyReview request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.MY_REVIEW_LIST_SUCCESS,
                reviewService.getMyReviews(request)
        );
    }
}
