package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public ReviewResDTO.ReviewResult writeReview(Long missionId, ReviewReqDTO.CreateReview request) {
        return ReviewResDTO.ReviewResult.builder()
                .reviewId(1L)
                .rate("⭐⭐⭐⭐⭐")
                .content("content")
                .imageUrls(request.imageUrls())
                .build();

    }}