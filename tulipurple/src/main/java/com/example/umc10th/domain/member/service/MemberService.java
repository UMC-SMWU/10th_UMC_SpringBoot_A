package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    // 홈화면 조회
    public MemberResDTO.HomeInfo getHome(Long userId, Address location, Pageable pageable) {
        // 멤버 찾기
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 지역 선택 (default: 회원의 address)
        Address selectedLocation = (location != null) ? location : member.getAddress();
        //현재 지역의 도전이 가능한 미션 조회
        Page<MemberMission> missionPage = memberMissionRepository
                .findOngoingByMemberAndAddress(userId, selectedLocation, pageable);

        // 성공한 미션 수와 전체 미션 수
        int successCount = (int) memberMissionRepository
                .countByMemberIdAndIsComplete(userId, true);
        int totalCount = (int) memberMissionRepository.countByMemberId(userId);

        return MemberConverter.toHomeInfo(member, selectedLocation, missionPage, successCount, totalCount);
    }

    // 회원가입
    public MemberResDTO.SignUpInfo signUp(MemberReqDTO.SignUp dto) {
        Member member = MemberConverter.toEntity(dto);
        Member savedMember = memberRepository.save(member);
        return MemberConverter.toSignUpInfo(savedMember);
    }
}
