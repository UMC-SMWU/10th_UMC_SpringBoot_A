package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    // 홈화면 조회
    public MemberResDTO.HomeInfo getHome(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        List<MemberMission> ongoingMissions = memberMissionRepository
                .findByMemberIdAndIsComplete(userId, false);
        int successCount = (int) memberMissionRepository
                .countByMemberIdAndIsComplete(userId, true);
        int totalCount = (int) memberMissionRepository.countByMemberId(userId);

        return MemberConverter.toHomeInfo(member, ongoingMissions, successCount, totalCount);
    }

    // 회원가입
    public MemberResDTO.SignUpInfo signUp(MemberReqDTO.SignUp dto) {
        Member member = MemberConverter.toEntity(dto);
        Member savedMember = memberRepository.save(member);
        return MemberConverter.toSignUpInfo(savedMember);
    }
}
