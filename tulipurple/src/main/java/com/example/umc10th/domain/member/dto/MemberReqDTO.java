package com.example.umc10th.domain.member.dto;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(
            Long id
    ){}

    // 회원가입
    public record SignUp(
            String userId,
            String password,
            String name,
            String gender,
            LocalDate birth,
            String address,
            List<String> food
    ){}
}