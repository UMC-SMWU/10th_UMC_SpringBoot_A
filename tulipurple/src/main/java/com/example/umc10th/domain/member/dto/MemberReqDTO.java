package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(
            Long id
    ){}

    // 회원가입
    public record SignUp(
            String email,
            String password,
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress,
            List<String> food
    ){}
}