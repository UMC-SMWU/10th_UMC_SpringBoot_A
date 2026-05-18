package com.example.umc10th.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class SignupReqDTO {

    private String name;
    private String gender;
    private String birthDate;
    private String address;
    private String addressDetail;
    private String email;
    private String password;
    private String phoneNumber;
}