package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResDTO.ReviewResult writeReview(Long storeId, ReviewReqDTO.CreateReview request) {
        return ReviewResDTO.ReviewResult.builder()
                .reviewId(1L)
                .rate("⭐⭐⭐⭐⭐")
                .content("content")
                .imageUrls(request.imageUrls())
                .build();
    }

    // 리뷰 목록 조회
    public ReviewResDTO.ReviewPreViewListDTO getReviews(Long storeId, Integer page) {
        return ReviewConverter.toReviewPreViewListDTO(
                reviewRepository.findByStoreId(storeId, PageRequest.of(page, 10))
        );
    }
}