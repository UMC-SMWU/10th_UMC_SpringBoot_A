package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.security.SecurityErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        SecurityErrorCode errorCode = SecurityErrorCode.UNAUTHORIZED;

        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(toJson(errorCode.getCode(), errorCode.getMessage()));
    }

    private String toJson(String code, String message) {
        return String.format(
                "{\"isSuccess\":false,\"code\":\"%s\",\"message\":\"%s\",\"result\":null}",
                code, message
        );
    }
}