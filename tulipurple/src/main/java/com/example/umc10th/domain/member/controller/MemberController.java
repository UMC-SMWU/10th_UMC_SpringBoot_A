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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    // 로그인 폼 페이지
    @GetMapping("/auth/login")
    public ResponseEntity<String> loginPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout
    ) {
        String message = "";
        if (error != null) {
            message = "<p style='color:red;margin:0 0 12px'>이메일 또는 비밀번호가 올바르지 않습니다.</p>";
        } else if (logout != null) {
            message = "<p style='color:green;margin:0 0 12px'>로그아웃되었습니다.</p>";
        }

        String html = """
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <title>로그인</title>
                    <style>
                        body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background: #f5f5f5; }
                        form { display: flex; flex-direction: column; gap: 12px; padding: 40px; background: white; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,.1); min-width: 300px; }
                        h2 { margin: 0 0 8px; text-align: center; }
                        input { padding: 10px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; }
                        button { padding: 10px; background: #b39ddb; color: white; border: none; cursor: pointer; border-radius: 4px; font-size: 16px; }
                        button:hover { background: #9575cd; }
                    </style>
                </head>
                <body>
                    <form method="post" action="/api/auth/login">
                        <h2>Please sign in</h2>
                        %s
                        <input type="email" name="email" placeholder="이메일" required />
                        <input type="password" name="password" placeholder="비밀번호" required />
                        <button type="submit">로그인</button>
                    </form>
                </body>
                </html>
                """.formatted(message);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }
}