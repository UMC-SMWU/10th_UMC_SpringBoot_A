package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Address;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MemberConverter {

    // 마이페이지
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    // 홈화면 조회
    public static MemberResDTO.HomeInfo toHomeInfo(
            Member member,
            Address location,
            Page<MemberMission> missionPage,
            int successCount,
            int totalCount
    ) {
        List<MemberResDTO.HomeMissionInfo> missionList = missionPage.stream()
                .map(MemberConverter::toHomeMissionInfo)
                .toList();

        return MemberResDTO.HomeInfo.builder()
                .location(location.name())
                .point(member.getPoint())
                .missionSuccessCount(successCount)
                .missionTotalCount(totalCount)
                .missionList(missionList)
                .build();
    }

    private static MemberResDTO.HomeMissionInfo toHomeMissionInfo(MemberMission mm) {
        long dDay = ChronoUnit.DAYS.between(LocalDate.now(), mm.getMission().getDeadline());
        return MemberResDTO.HomeMissionInfo.builder()
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .conditional(mm.getMission().getConditional())
                .point(mm.getMission().getPoint())
                .deadline(mm.getMission().getDeadline())
                .dDay(dDay)
                .build();
    }

    // 회원가입
    public static Member toEntity(MemberReqDTO.SignUp dto) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .build();
    }

    public static MemberResDTO.SignUpInfo toSignUpInfo(Member member) {
        return MemberResDTO.SignUpInfo.builder()
                .memberId(member.getId())
                .build();
    }
}
