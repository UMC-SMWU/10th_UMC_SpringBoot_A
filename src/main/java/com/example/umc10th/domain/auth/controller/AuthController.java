package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.auth.exception.code.AuthSuccessCode;
import com.example.umc10th.domain.auth.service.AuthService;
import com.example.umc10th.domain.auth.service.OnboardingService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final OnboardingService onboardingService;

    //야매 회원가입
    @PostMapping("/users")
    public ApiResponse<AuthResDTO.SignUpResult> signUp(
            @RequestBody AuthReqDTO.SignUp dto
    ) {
        return ApiResponse.onSuccess(AuthSuccessCode.SIGNUP_SUCCESS,authService.signUp(dto));
    }

    //온보딩
    @PostMapping("/onboarding/{memberId}")
    public ApiResponse<AuthResDTO.OnboardingResult> onboarding(
            @PathVariable Long memberId,
            @RequestBody AuthReqDTO.Onboarding dto
    ){
        return ApiResponse.onSuccess(AuthSuccessCode.ONBOARDING_SUCCESS, onboardingService.onboarding(memberId, dto));
    }
}
