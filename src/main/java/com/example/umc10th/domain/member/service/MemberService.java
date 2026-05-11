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

    // 미션1: 진행중인 미션 조회 (오프셋 페이지네이션)
    public MemberResDTO.MissionPageResponse getChallengingMissions(
            MemberReqDTO.MissionPageRequest request, Integer page) {

        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<MemberMission> result = memberMissionRepository
                .findByMemberIdAndStatus(request.memberId(), MissionStatus.CHALLENGING, pageRequest);
        return MemberConverter.toMissionPageResponse(result);
    }
    // 미션2: 내가 작성한 리뷰 목록 (커서 페이지네이션)
    public MemberResDTO.ReviewPageResponse getMyReviews(
            Long memberId, String cursor, Integer pageSize) {

        // cursor가 없으면 처음부터, 있으면 해당 id 이후부터 조회
        List<Review> reviews;
        if (cursor == null || cursor.equals("-1")) {
            reviews = reviewRepository.findByMemberIdOrderByIdDesc(
                    memberId, PageRequest.of(0, pageSize));
        } else {
            reviews = reviewRepository.findByMemberIdAndIdLessThanOrderByIdDesc(
                    memberId, Long.parseLong(cursor), PageRequest.of(0, pageSize));
        }

        return MemberConverter.toReviewPageResponse(reviews, pageSize);
    }
}