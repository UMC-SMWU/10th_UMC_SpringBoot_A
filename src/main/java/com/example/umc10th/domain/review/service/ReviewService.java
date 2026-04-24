package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewPhotoRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    public ReviewResDTO.CreateReviewResult createReview(ReviewReqDTO.CreateReview dto) {

        MemberMission memberMission = memberMissionRepository.findById(dto.memberMissionId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MEMBER_MISSION_NOT_FOUND));

        if (memberMission.getIsCompleted() != MissionCompleteStatus.COMPLETED) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_ALLOWED);
        }

        if (dto.rating() < 1 || dto.rating() > 5) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING);
        }

        if (dto.reviewContent() == null || dto.reviewContent().isBlank()) {
            throw new ReviewException(ReviewErrorCode.EMPTY_REVIEW_CONTENT);
        }

        Review review = Review.builder()
                .member(memberMission.getMember())
                .store(memberMission.getStore())
                .star(dto.rating())
                .reviewContent(dto.reviewContent())
                .build();

        Review savedReview = reviewRepository.save(review);

        if (dto.photoUrls() != null) {
            for (String photoUrl : dto.photoUrls()) {
                ReviewPhoto reviewPhoto = ReviewPhoto.builder()
                        .review(savedReview)
                        .photoUrl(photoUrl)
                        .build();

                reviewPhotoRepository.save(reviewPhoto);
            }
        }

        return new ReviewResDTO.CreateReviewResult(
                savedReview.getId(),
                savedReview.getStore().getId(),
                savedReview.getStar(),
                savedReview.getReviewContent()
        );
    }
}
