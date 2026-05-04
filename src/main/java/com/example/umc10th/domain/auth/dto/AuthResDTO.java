package com.example.umc10th.domain.auth.dto;

public class AuthResDTO {

    public record SignUpResult (
            Long memberId,
<<<<<<< HEAD
            String email
    ){}

    public record OnboardingResult(
            Long memberId,
=======
            String email,
>>>>>>> upstream/Kim-DongH
            String name
    ){}
}
