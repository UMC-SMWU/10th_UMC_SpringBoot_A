package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    // // 마이페이지
    // @PostMapping("/v1/users/me")
    // public ApiResponse<MemberResDTO.GetInfo> getInfo(
    //         @RequestBody MemberReqDTO.GetInfo dto
    // ) {
    //     BaseSuccessCode code = MemberSuccessCode.OK;
    //     return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    // }

    // 홈화면 조회
    @GetMapping("/api/users/{userId}/home")
    public ApiResponse<MemberResDTO.HomeInfo> getHome(
            @PathVariable Long userId
    ) {
        BaseSuccessCode code = MemberSuccessCode.HOME_OK;
        return ApiResponse.onSuccess(code, null);
    }

    // 회원가입
    @PostMapping("/api/auth/signup")
    public ApiResponse<MemberResDTO.SignUpInfo> signUp(
            @RequestBody MemberReqDTO.SignUp dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.SIGN_UP_OK;
        return ApiResponse.onSuccess(code, null);
    }
}