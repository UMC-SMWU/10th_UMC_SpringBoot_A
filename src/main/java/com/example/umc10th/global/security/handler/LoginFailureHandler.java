package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.security.SecurityErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        SecurityErrorCode errorCode = SecurityErrorCode.LOGIN_FAILED;

        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(String.format(
                "{\"isSuccess\":false,\"code\":\"%s\",\"message\":\"%s\",\"result\":null}",
                errorCode.getCode(), errorCode.getMessage()
        ));
    }
}