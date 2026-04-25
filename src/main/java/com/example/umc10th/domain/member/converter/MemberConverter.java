package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberConverter {

    // SignUpDTO → Member 엔티티
    public static Member toMember(MemberReqDTO.SignUpDTO request) {
        return Member.builder()
                .name(request.getName())
                .gender(Gender.valueOf(request.getGender()))  // String → Enum 변환
                .birthDate(LocalDate.parse(request.getBirthDate()))  // String → LocalDate 변환
                .address(request.getAddress())
                .build();
    }

    // Member → SignUpResultDTO
    public static MemberResDTO.SignUpResultDTO toSignUpResultDTO(Member member) {
        return MemberResDTO.SignUpResultDTO.builder()
                .memberId(member.getId())
                .name(member.getName())
                .build();
    }
}