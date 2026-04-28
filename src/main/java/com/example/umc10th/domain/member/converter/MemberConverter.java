package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;

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
}