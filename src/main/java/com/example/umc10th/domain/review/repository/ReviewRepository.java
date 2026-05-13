package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMember(Member member);

    //ID순 커서 조회
    List<Review> findAllByMember_IdAndIdLessThanOrderByIdDesc(
            Long memberId,
            Long cursorId,
            Pageable pageable
    );

    //ID순 조회
    List<Review> findAllByMember_IdOrderByIdDesc(
            Long memberId,
            Pageable pageable
    );

    //별점순 커서 조회
    @Query("""
            SELECT r FROM Review r
            WHERE r.member.id = :memberId
            AND (
                :cursorScore IS NULL
                OR r.star < :cursorScore
                OR (r.star = :cursorScore AND r.id < :cursorId)
            )
            ORDER BY r.star DESC, r.id DESC
            """)
    List<Review> findMyReviewsByScoreCursor(
            @Param("memberId") Long memberId,
            @Param("cursorScore") Double cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}