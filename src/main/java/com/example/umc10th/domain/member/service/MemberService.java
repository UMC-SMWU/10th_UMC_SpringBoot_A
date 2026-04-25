package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    public MemberResDTO.SignUpResultDTO signUp(MemberReqDTO.SignUpDTO request) {
        return MemberResDTO.SignUpResultDTO.builder()
                .memberId(1L)
                .name("양세영")
                .email("im3zero@email.com")
                .reviews(List.of("1"))
                .build();
    }

    public MemberResDTO.HomeDTO getHome() {
        return MemberResDTO.HomeDTO.builder()
                .memberId(1L)
                .name("세영")
                .email("im3zero@email.com")
                .reviews(List.of())
                .build();
    }

    public MemberResDTO.LoginResultDTO login(MemberReqDTO.LoginDTO request) {
        return MemberResDTO.LoginResultDTO.builder()
                .email(request.getEmail())
                .token("임시 토큰")
                .build();
    }
}