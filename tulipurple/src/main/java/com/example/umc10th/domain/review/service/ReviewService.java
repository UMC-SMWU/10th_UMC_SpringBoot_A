package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성
    @Transactional
    public ReviewResDTO.ReviewInfo writeReview(Long userId, ReviewReqDTO.WriteReview dto) {
        // 누가 적었니
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // 어느 가게니
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        // 엔티티 만들기
        Review review = ReviewConverter.toEntity(dto, member, store);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toReviewInfo(savedReview);
    }

    // 내 리뷰 조회
    public MissionResDTO.Pagination<ReviewResDTO.GetReview> getMyReviews(
            Long userId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우
        if (!cursor.equals("-1")) {

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findReviewsByMember_IdAndIdLessThanOrderByIdDesc(userId, idCursor, pageRequest);
                    break;
                case "star":
                    Float starCursor = Float.parseFloat(cursorSplit[0]);
                    Long starIdCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findReviewsByMember_IdOrderByStarDescWithCursor(userId, starCursor, starIdCursor, pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else {
            // 커서 없이 조회
            switch (query.toLowerCase()) {
                case "id":
                    reviewList = reviewRepository.findReviewsByMember_IdOrderByIdDesc(userId, pageRequest);
                    break;
                case "star":
                    reviewList = reviewRepository.findReviewsByMember_IdOrderByStarDescIdDesc(userId, pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        // 다음 커서 계산
        Review lastReview = reviewList.getContent().getLast();
        nextCursor = lastReview.getStar() + ":" + lastReview.getId();

        // 응답 DTO로 포장
        return MissionConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
