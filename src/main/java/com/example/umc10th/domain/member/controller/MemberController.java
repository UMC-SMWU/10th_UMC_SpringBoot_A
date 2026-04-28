package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;

@RestController //JSON형식의 응답을 내보내기 위한 컨트롤러 (@Controller + @ResponseBody 합친 어노테이션)
@RequiredArgsConstructor //생성자 주입을 위한 어노테이션
public class MemberController {
    private final MemberService memberService;

    //request body
    // 홈화면
    @GetMapping("/api/users/home")
    public ApiResponse<MemberResDTO.HomeInfo> getInfo(
            @RequestParam Long memberId)
    {
        BaseSuccessCode code = MemberSuccessCode.HOME_SUCCESS;
        return ApiResponse.onSuccess(code, memberService.getInfo());
    }


    // 회원가입
    @PostMapping("/api/signup")
    public ApiResponse<MemberResDTO.SignUp> signUp(
            @RequestBody MemberReqDTO.SignUp dto)
    {
        BaseSuccessCode code = MemberSuccessCode.SIGN_UP_SUCCESS;
        MemberResDTO.SignUp result = memberService.signUp(dto);
        return ApiResponse.onSuccess(code, result);
    }

    // 로그인
    @PostMapping("/api/auth/login")
    public ApiResponse<MemberResDTO.Login> login(
            @RequestBody MemberReqDTO.Login dto)
    {
        BaseSuccessCode code = MemberSuccessCode.LOGIN_SUCCESS;
        MemberResDTO.Login result = memberService.login(dto);  // ← 이 줄 추가!
        return ApiResponse.onSuccess(code, result);
    }


}