package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 리뷰 화면: 가게의 리뷰 목록
    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId ORDER BY r.createdAt DESC")


    Page<Review> findByStoreId(
            @Param("storeId") Long storeId,
            Pageable pageable
    );

    // 추가: 내 리뷰 목록 - 처음 조회 (커서 없음)
    List<Review> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // 추가: 내 리뷰 목록 - 커서 이후 조회
    List<Review> findByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long cursor, Pageable pageable);

}