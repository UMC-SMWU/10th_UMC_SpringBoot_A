package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    /**
     * 리뷰 생성 (Restaurant 조회 없이 ID만 사용)
     */
    @Transactional
    public ReviewResDTO.CreateReview createReview(Long userId, ReviewReqDTO.CreateReview request) {

        // 1. userId로 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        // 2. Restaurant은 조회 안 하고 ID만 세팅
        Restaurant restaurant = Restaurant.builder()
                .id(request.restaurantId())
                .build();

        // 3. Review 생성
        Review review = Review.builder()
                .user(user)
                .restaurant(restaurant)
                .content(request.content())
                .starRating((float) request.star())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // 4. 저장
        Review saved = reviewRepository.save(review);

        // 5. 응답 DTO 반환
        return ReviewResDTO.CreateReview.builder()
                .reviewId(saved.getId())
                .title(request.title())
                .star(request.star())
                .content(request.content())
                .build();
    }
}