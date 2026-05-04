package com.example.umc10th.domain.auth.dto;

import com.example.umc10th.domain.member.enums.Gender;
import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {

    public record SignUp (
            String email,
<<<<<<< HEAD
            String password
    ){}

    public record Onboarding (
            String name,
            Gender gender,
            LocalDate birth,
=======
            String password,
            String name,
            Gender gender,
            LocalDate birthDate,
>>>>>>> upstream/Kim-DongH
            String address,
            List<Long> preferredFoodCategoryIds,
            List<Long> agreedTermIds
    ){}
}
