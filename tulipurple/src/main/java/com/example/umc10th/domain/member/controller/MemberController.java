package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지
    @GetMapping("/users/{userId}/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @PathVariable Long userId
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(userId));
    }

    // 홈화면 조회
    @GetMapping("/users/{userId}/home")
    public ApiResponse<MemberResDTO.HomeInfo> getHome(
            @PathVariable Long userId,
            @RequestParam(required = false) Address location,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        BaseSuccessCode code = MemberSuccessCode.HOME_OK;
        return ApiResponse.onSuccess(code, memberService.getHome(userId, location, PageRequest.of(pageNumber, pageSize)));
    }

    // 회원가입
    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.SignUpInfo> signUp(
            @RequestBody MemberReqDTO.SignUp dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.SIGN_UP_OK;
        return ApiResponse.onSuccess(code, memberService.signUp(dto));
    }
}