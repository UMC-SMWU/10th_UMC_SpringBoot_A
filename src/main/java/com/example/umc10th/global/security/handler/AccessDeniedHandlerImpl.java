package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.security.SecurityErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        SecurityErrorCode errorCode = SecurityErrorCode.FORBIDDEN;

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