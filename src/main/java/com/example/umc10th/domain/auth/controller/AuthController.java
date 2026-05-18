package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.auth.dto.SignupReqDTO;
import com.example.umc10th.domain.auth.exception.code.AuthSuccessCode;
import com.example.umc10th.domain.auth.service.AuthService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(
            @RequestBody SignupReqDTO request
    ) {

        authService.signup(request);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(AuthSuccessCode.SIGNUP_SUCCESS, null)
        );
    }
}