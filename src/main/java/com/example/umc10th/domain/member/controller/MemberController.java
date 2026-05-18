package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor //생성자 주입을 위한 어노테이션
public class MemberController {
    private final MemberService memberService;

    //request body
    // 홈화면
    @GetMapping("/users/home")
    public ApiResponse<MemberResDTO.HomeInfo> getInfo(
            @RequestParam Long memberId)
    {
        BaseSuccessCode code = MemberSuccessCode.HOME_SUCCESS;
        return ApiResponse.onSuccess(code, memberService.getInfo());
    }


    // 회원가입
    @PostMapping("/users/signup")
    public ApiResponse<MemberResDTO.SignUp> signUp(
            @RequestBody @Valid MemberReqDTO.SignUp dto)
    {
        BaseSuccessCode code = MemberSuccessCode.SIGN_UP_SUCCESS;
        MemberResDTO.SignUp result = memberService.signUp(dto);
        return ApiResponse.onSuccess(code, result);
    }

    // 로그인
    @PostMapping("/auth/login")
    public ApiResponse<MemberResDTO.Login> login(
            @RequestBody MemberReqDTO.Login dto)
    {
        BaseSuccessCode code = MemberSuccessCode.LOGIN_SUCCESS;
        MemberResDTO.Login result = memberService.login(dto);  // ← 이 줄 추가!
        return ApiResponse.onSuccess(code, result);
    }

    @GetMapping("/users/{memberId}/my")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(
            @PathVariable Long memberId) {
        return ApiResponse.onSuccess(memberService.getMyPage(memberId));
    }

    // 미션1: 진행중인 미션 조회 (오프셋 페이지네이션)
// userId는 RequestBody에서 받기
    @PostMapping("/members/missions/challenging")
    public ApiResponse<MemberResDTO.MissionPageResponse> getChallengingMissions(
            @RequestBody @Valid MemberReqDTO.MissionPageRequest request,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(memberService.getChallengingMissions(request, page));
    }

    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<MemberResDTO.ReviewPageResponse> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false, defaultValue = "-1") String cursor,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy  // 추가!
    ) {
        return ApiResponse.onSuccess(memberService.getMyReviews(memberId, cursor, pageSize, sortBy));
    }

}