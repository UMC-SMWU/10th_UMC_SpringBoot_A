package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
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
    public ResponseEntity<ReviewResDTO.CreateResultDTO> writeReview(
            @PathVariable Long missionId,
            @RequestBody ReviewReqDTO.CreateDTO request,
            @RequestHeader("Authorization") String accessToken) {
        // TODO: reviewService.write(missionId, request) 연결
        return ResponseEntity.ok(null);
    }
}