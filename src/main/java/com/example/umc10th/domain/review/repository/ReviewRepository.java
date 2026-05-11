package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 기존: 가게의 리뷰 목록
    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId ORDER BY r.createdAt DESC")
    Page<Review> findByStoreId(@Param("storeId") Long storeId, Pageable pageable);

    // ID 순 - 처음 조회
    List<Review> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // ID 순 - 커서 이후 조회
    List<Review> findByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long cursor, Pageable pageable);

    // 별점 순 - 처음 조회
    List<Review> findByMemberIdOrderByScoreDesc(Long memberId, Pageable pageable);

    // 별점 순 - 커서 이후 조회
    List<Review> findByMemberIdAndScoreLessThanOrderByScoreDesc(Long memberId, Integer cursor, Pageable pageable);
}