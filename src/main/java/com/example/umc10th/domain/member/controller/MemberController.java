package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;


//    @PostMapping("/v1/member/me")
//    public ApiResponse<MemberResDTO.GetInfo> getInfo(@RequestBody MemberReqDTO.GetInfo dto){
//        BaseSuccessCode code = MemberSuccessCode.OK;
//        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
//    }

    //홈 화면 조회
    @GetMapping("/home")
    public ApiResponse<MemberResDTO.HomeInfo> getHome(
            @RequestHeader("Authorization") String authorization
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getHome());
    }

    //마이페이지 조회
    @GetMapping("/members/me")
    public ApiResponse<MemberResDTO.MyPage> getMyPage(
            @RequestHeader("Authorization") String authorization
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getMyPage());
    }
}
