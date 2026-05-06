package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toEntity(ReviewReqDTO.WriteReview dto, Member member, Store store) {
        return Review.builder()
                .content(dto.content())
                .star(dto.star())
                .createdAt(LocalDateTime.now())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewPhoto toReviewPhotoEntity(String photoUrl, Review review) {
        return ReviewPhoto.builder()
                .photoUrl(photoUrl)
                .review(review)
                .build();
    }

    public static ReviewResDTO.ReviewInfo toReviewInfo(Review review) {
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .build();
    }
}
