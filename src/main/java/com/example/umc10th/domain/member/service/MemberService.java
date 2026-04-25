package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    public MemberResDTO.SignUpResultDTO signUp(MemberReqDTO.SignUpDTO request) {
        // 실제 로직 구현
        return null;
    }

    public MemberResDTO.HomeDTO getHome() {
        //실제 로직 구현
        return null;
    }

    public String login(MemberReqDTO.LoginDTO request) {
        //실제 로직 구현
        return null;
    }
}