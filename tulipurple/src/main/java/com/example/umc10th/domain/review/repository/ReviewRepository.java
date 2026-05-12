package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 기준
    Slice<Review> findReviewsByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);
    Slice<Review> findReviewsByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, Long idCursor, Pageable pageable);

    // 별점 기준
    Slice<Review> findReviewsByMember_IdOrderByStarDescIdDesc(Long memberId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND (r.star < :starCursor OR (r.star = :starCursor AND r.id < :idCursor)) ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findReviewsByMember_IdOrderByStarDescWithCursor(@Param("memberId") Long memberId, @Param("starCursor") Float starCursor, @Param("idCursor") Long idCursor, Pageable pageable);
}