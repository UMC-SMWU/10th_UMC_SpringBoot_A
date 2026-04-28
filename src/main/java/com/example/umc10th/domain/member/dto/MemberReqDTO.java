package com.example.umc10th.domain.member.dto;

//클라이언트 -> 서버로 보내는 데이터
public class MemberReqDTO {
    public record GetInfo(Long memberId, String password){
    }

    public record HomeInfo(Long memberId, String password, Integer point) {
    }

    public record SignUp(String name, String gender, String email, String phoneNumber, String birthday_Date, String address) {
    }

    public record Login(String email, String password) {
    }
}
