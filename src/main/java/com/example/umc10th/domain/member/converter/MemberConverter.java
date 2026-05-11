package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import java.util.List;

import java.time.LocalDate;

public class MemberConverter {

    // SignUpDTO → Member 엔티티
    public static Member toMember(MemberReqDTO.SignUp request) {
        return Member.builder()
                .name(request.name())
                .gender(Gender.valueOf(request.gender()))  // String → Enum 변환
                .birthDate(LocalDate.parse(request.birthday_Date()))  // String → LocalDate 변환
                .address(request.address())
                .build();
    }

    // Member → SignUpResult
    public static MemberResDTO.SignUp toSignUp(Member member) {
        return MemberResDTO.SignUp.builder()
                .memberId(member.getId())
                .name(member.getName())
                .build();
    }

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member) {
        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getName())
                .email(member.getEmail())
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.MissionInfo toMissionInfo(MemberMission memberMission) {
        return MemberResDTO.MissionInfo.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus().name())
                .build();
    }

    public static MemberResDTO.MissionPageResponse toMissionPageResponse(Page<MemberMission> page) {
        List<MemberResDTO.MissionInfo> missionInfoList = page.stream()
                .map(MemberConverter::toMissionInfo)
                .toList();

        return MemberResDTO.MissionPageResponse.builder()
                .missionList(missionInfoList)
                .listSize(missionInfoList.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static MemberResDTO.ReviewPageResponse toReviewPageResponse(
            List<Review> reviews, Integer pageSize) {

        boolean hasNext = reviews.size() > pageSize;
        List<Review> pageContent = hasNext ? reviews.subList(0, pageSize) : reviews;

        String nextCursor = hasNext
                ? String.valueOf(pageContent.get(pageContent.size() - 1).getId())
                : null;

        List<MemberResDTO.ReviewInfo> reviewInfoList = pageContent.stream()
                .map(r -> MemberResDTO.ReviewInfo.builder()
                        .reviewId(r.getId())
                        .storeName(r.getStore().getName())
                        .star((float) r.getScore())
                        .body(r.getBody())
                        .createdAt(r.getCreatedAt().toLocalDate())
                        .build())
                .toList();

        return MemberResDTO.ReviewPageResponse.builder()
                .reviewList(reviewInfoList)
                .listSize(pageContent.size())
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }


}