package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import java.util.List;

public class ReviewConverter {

    // 리뷰 리스트 변환
    public static ReviewResDTO.MyReviewList toMyReviewList(
            List<Review> reviews,
            Boolean hasNext
    ) {
        List<ReviewResDTO.MyReview> reviewList = reviews.stream()
                .map(review -> {
                    String replyContent = null;

                    if (review.getReply() != null) {
                        replyContent = review.getReply().getReplyContent();
                    }

                    return ReviewResDTO.MyReview.builder()
                            .reviewId(review.getId())
                            .storeName(review.getStore().getStoreName())
                            .star(review.getStar())
                            .reviewContent(review.getReviewContent())
                            .reply(replyContent)
                            .build();
                })
                .toList();

        Long nextCursorId = null;
        Integer nextCursorStar = null;

        if (!reviews.isEmpty()) {
            Review lastReview = reviews.get(reviews.size() - 1);
            nextCursorId = lastReview.getId();
            nextCursorStar = lastReview.getStar();
        }

        return ReviewResDTO.MyReviewList.builder()
                .reviews(reviewList)
                .hasNext(hasNext)
                .nextCursorId(nextCursorId)
                .nextCursorStar(nextCursorStar)
                .build();
    }
}
