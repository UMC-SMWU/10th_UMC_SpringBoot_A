package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MemberConverter {

    // 마이페이지
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .gender(member.getGender())
                .birth(member.getBirth())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    // 홈화면 조회
    public static MemberResDTO.HomeInfo toHomeInfo(
            Member member,
            List<MemberMission> ongoingMissions,
            int successCount,
            int totalCount
    ) {
        List<MemberResDTO.HomeMissionInfo> missionList = ongoingMissions.stream()
                .map(MemberConverter::toHomeMissionInfo)
                .toList();

        return MemberResDTO.HomeInfo.builder()
                .name(member.getName())
                .point(member.getPoint())
                .missionSuccessCount(successCount)
                .missionTotalCount(totalCount)
                .missionList(missionList)
                .build();
    }

    private static MemberResDTO.HomeMissionInfo toHomeMissionInfo(MemberMission mm) {
        return MemberResDTO.HomeMissionInfo.builder()
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .conditional(mm.getMission().getConditional())
                .point(mm.getMission().getPoint())
                .deadline(mm.getMission().getDeadline())
                .build();
    }

    // 회원가입
    public static Member toEntity(MemberReqDTO.SignUp dto) {
        return Member.builder()
                .name(dto.name())
                .email(dto.userId())
                .password(dto.password())
                .build();
    }

    public static MemberResDTO.SignUpInfo toSignUpInfo(Member member) {
        return MemberResDTO.SignUpInfo.builder()
                .memberId(member.getId())
                .build();
    }
}
