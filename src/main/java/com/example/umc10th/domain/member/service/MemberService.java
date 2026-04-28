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

    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp request) {
        //service -> controller
        return MemberResDTO.SignUp.builder()
                .memberId(1L)
                .name("양세영")
                .email("im3zero@email.com")
                .build();
    }

    public MemberResDTO.HomeInfo getInfo() {
        return MemberResDTO.HomeInfo.builder()
                .memberId(1L)
                .name("세영")
                .email("im3zero@email.com")
                .reviews(List.of())
                .build();
    }

    public MemberResDTO.Login login(MemberReqDTO.Login request) {
        return MemberResDTO.Login.builder()
                .email("im3zero@email.com")
                .build();
    }
}