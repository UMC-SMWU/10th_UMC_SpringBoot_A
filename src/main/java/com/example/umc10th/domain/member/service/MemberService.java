package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp request) {
        return MemberResDTO.SignUp.builder()
                .memberId(1L)
                .name("양세영")
                .email("im3zero@email.com")
                .build();
    }

    public MemberResDTO.HomeInfo getInfo() {
        return MemberResDTO.HomeInfo.builder()
                .memberId(1L)
                .name("세영")
                .email("im3zero@email.com")
                .reviews(List.of())
                .build();
    }

    public MemberResDTO.Login login(MemberReqDTO.Login request) {
        return MemberResDTO.Login.builder()
                .email("im3zero@email.com")
                .build();
    }

    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        return MemberConverter.toMyPageDTO(member);
    }

    // 미션1: 진행중인 미션 조회
    public MemberResDTO.MissionPageResponse getChallengingMissions(
            MemberReqDTO.MissionPageRequest request, Integer page) {

        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<MemberMission> result = memberMissionRepository
                .findByMemberIdAndStatus(request.memberId(), MissionStatus.CHALLENGING, pageRequest);
        return MemberConverter.toMissionPageResponse(result);
    }

    public MemberResDTO.ReviewPageResponse getMyReviews(
            Long memberId, String cursor, Integer pageSize, String sortBy) {

        List<Review> reviews;
        boolean isSortByScore = "star".equals(sortBy);

        if (cursor == null || cursor.equals("-1")) {
            reviews = isSortByScore
                    ? reviewRepository.findByMemberIdOrderByScoreDesc(memberId, PageRequest.of(0, pageSize + 1))
                    : reviewRepository.findByMemberIdOrderByIdDesc(memberId, PageRequest.of(0, pageSize + 1));
        } else {
            reviews = isSortByScore
                    ? reviewRepository.findByMemberIdAndScoreLessThanOrderByScoreDesc(memberId, Integer.parseInt(cursor), PageRequest.of(0, pageSize + 1))
                    : reviewRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, Long.parseLong(cursor), PageRequest.of(0, pageSize + 1));
        }

        return MemberConverter.toReviewPageResponse(reviews, pageSize);
    }

}