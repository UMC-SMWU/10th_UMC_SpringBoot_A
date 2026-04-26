package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mypage")
public class UserController {

    // 내 정보 조회
    @GetMapping("/me")
    public ApiResponse<UserResDTO.GetInfo> getMyPage() {

        UserResDTO.GetInfo response =
                UserResDTO.GetInfo.builder()
                        .name("김다은")
                        .nickname("daeun")
                        .profileUrl("https://example.com/profile.jpg")
                        .email("daeun@example.com")
                        .phoneNumber("010-1234-5678")
                        .point(1500)
                        .build();

        return ApiResponse.onSuccess(
                UserSuccessCode.USER_INFO_SUCCESS,
                response
        );
    }

    // 내 정보 수정
    @PatchMapping("/me")
    public ApiResponse<UserResDTO.GetInfo> updateMyPage(
            @RequestBody UserReqDTO.UpdateInfo request
    ) {

        // 일단 가짜 데이터 넣기
        UserResDTO.GetInfo response =
                UserResDTO.GetInfo.builder()
                        .name(request.name())
                        .nickname(request.nickname())
                        .profileUrl("https://example.com/profile.jpg")
                        .email(request.email())
                        .phoneNumber(request.phoneNumber())
                        .point(1500)
                        .build();

        return ApiResponse.onSuccess(
                UserSuccessCode.USER_UPDATE_SUCCESS,
                response
        );
    }
}