package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
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

    public ReviewResDTO.ReviewList getMyReviews(
            Long userId,
            Long cursor,
            int size,
            String sort
    ) {

        Pageable pageable = PageRequest.of(0, size + 1);

        List<Review> reviews;

        // ID 순 조회
        if ("id".equalsIgnoreCase(sort)) {
            reviews = reviewRepository.findMyReviewsOrderById(
                    userId,
                    cursor,
                    pageable
            );
        }

        // 별점 순 조회
        else if ("star".equalsIgnoreCase(sort)) {
            reviews = reviewRepository.findMyReviewsOrderByStar(
                    userId,
                    cursor == null ? null : cursor.floatValue(),
                    pageable
            );
        }

        // 잘못된 sort 값
        else {
            throw new RuntimeException("정렬 조건이 올바르지 않습니다.");
        }

        boolean hasNext = reviews.size() > size;

        // size 개만 사용
        List<Review> content = hasNext
                ? reviews.subList(0, size)
                : reviews;

        List<ReviewResDTO.ReviewItem> reviewItems = content.stream()
                .map(review -> ReviewResDTO.ReviewItem.builder()
                        .reviewId(review.getId())
                        .restaurantName(review.getRestaurant().getName())
                        .star(review.getStarRating())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt().toString())
                        .build())
                .toList();

        Long nextCursor = null;

        // 다음 페이지 존재하면 마지막 review id 저장
        if (hasNext) {
            nextCursor = content.get(content.size() - 1).getId();
        }

        return ReviewResDTO.ReviewList.builder()
                .review(reviewItems)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}