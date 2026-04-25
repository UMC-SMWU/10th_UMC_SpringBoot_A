package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 홈화면
    @GetMapping("/api/users/home")
    public ResponseEntity<MemberResDTO.HomeDTO> getHome(
            @RequestHeader("Authorization") String accessToken) {
        // TODO: memberService.getHome() 연결
        return ResponseEntity.ok(null);
    }

    // 회원가입
    @PostMapping("/api/signup")
    public ResponseEntity<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody MemberReqDTO.SignUpDTO request) {
        // TODO: memberService.signUp(request) 연결
        return ResponseEntity.ok(null);
    }

    // 로그인
    @PostMapping("/api/auth/login")
    public ResponseEntity<String> login(
            @RequestBody MemberReqDTO.LoginDTO request) {
        // TODO: memberService.login(request) → 토큰 반환
        return ResponseEntity.ok(null);
    }
}