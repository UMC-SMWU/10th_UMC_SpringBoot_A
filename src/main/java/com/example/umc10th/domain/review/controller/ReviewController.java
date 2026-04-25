package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/missions/{missionId}/review")
    public ResponseEntity<ApiResponse<ReviewResDTO.CreateResultDTO>> writeReview(
            @PathVariable Long missionId,
            @RequestBody ReviewReqDTO.CreateDTO request) {
        ReviewResDTO.CreateResultDTO result = ReviewResDTO.CreateResultDTO.builder()
                .reviewId(1L)
                .build();
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
}