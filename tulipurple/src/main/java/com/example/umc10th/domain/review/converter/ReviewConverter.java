package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    // 작성 요청 -> 엔티티
    public static Review toEntity(ReviewReqDTO.WriteReview dto, Member member, Store store) {
        return Review.builder()
                .content(dto.content())
                .star(dto.star())
                .member(member)
                .store(store)
                .build();
    }

    // 엔티티 -> 작성 응답
    public static ReviewResDTO.ReviewInfo toReviewInfo(Review review) {
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .build();
    }

    // 엔티티 -> 조회 응답
    public static ReviewResDTO.GetReview toGetReview(Review review) {
        return ReviewResDTO.GetReview.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .star(review.getStar())
                .build();
    }
}
