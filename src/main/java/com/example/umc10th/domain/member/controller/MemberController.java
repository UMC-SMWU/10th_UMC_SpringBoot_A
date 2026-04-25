package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.umc10th.global.ApiResponse;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 홈화면
    @GetMapping("/api/users/home")
    public ResponseEntity<ApiResponse<MemberResDTO.HomeDTO>> getHome() {
        MemberResDTO.HomeDTO result = memberService.getHome();
        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.HOME_SUCCESS, result));
    }

    // 회원가입
    @PostMapping("/api/signup")
    public ResponseEntity<ApiResponse<MemberResDTO.SignUpResultDTO>> signUp(
            @RequestBody MemberReqDTO.SignUpDTO request) {
        MemberResDTO.SignUpResultDTO result = memberService.signUp(request);
        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP_SUCCESS, result));
    }

    // 로그인
    @PostMapping("/api/auth/login")
    public ResponseEntity<ApiResponse<MemberResDTO.LoginResultDTO>> login(
            @RequestBody MemberReqDTO.LoginDTO request) {
        MemberResDTO.LoginResultDTO result = memberService.login(request);
        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.LOGIN_SUCCESS, result));
    }


}