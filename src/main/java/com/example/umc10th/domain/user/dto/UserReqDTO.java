package com.example.umc10th.domain.user.dto;

public class UserReqDTO {

    public record UpdateInfo(
            String email,
            String nickname,
            String name,
            String phoneNumber,
            String password
    ) {}

}