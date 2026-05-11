package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class UserController {

    private final UserService userService;

    // 내 정보 조회
    @GetMapping("/me")
    public ApiResponse<UserResDTO.GetInfo> getMyPage() {

        Long userId = 1L;

        return ApiResponse.onSuccess(
                UserSuccessCode.USER_INFO_SUCCESS,
                userService.getMyPage(userId)
        );

    }

    // 내 정보 수정
    @PatchMapping("/me/info")
    public ApiResponse<UserResDTO.GetInfo> updateMyPage(
            @RequestBody UserReqDTO.UpdateInfo request
    ) {

        Long userId = 1L;

        UserResDTO.GetInfo response =
                userService.updateMyPage(userId, request);

        return ApiResponse.onSuccess(
                UserSuccessCode.USER_UPDATE_SUCCESS,
                response
        );
    }
}