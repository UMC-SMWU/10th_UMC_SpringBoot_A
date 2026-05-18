package com.example.umc10th.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class LoginReqDTO {

    private String email;
    private String password;
}