package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT r FROM Review r
        JOIN FETCH r.restaurant
        WHERE r.user.id = :userId
        AND (:cursor IS NULL OR r.id < :cursor)
        ORDER BY r.id DESC
    """)
    List<Review> findMyReviewsOrderById(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
        SELECT r FROM Review r
        JOIN FETCH r.restaurant
        WHERE r.user.id = :userId
        AND (:cursor IS NULL OR r.starRating < :cursor)
        ORDER BY r.starRating DESC, r.id DESC
    """)
    List<Review> findMyReviewsOrderByStar(
            @Param("userId") Long userId,
            @Param("cursor") Float cursor,
            Pageable pageable
    );

}