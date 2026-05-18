package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

//클라이언트 -> 서버로 보내는 데이터
public class MemberReqDTO {
    public record GetInfo(Long memberId, String password) {
    }

    public record HomeInfo(Long memberId, String password, Integer point) {
    }

    public record SignUp(
            @NotNull(message = "이름은 필수입니다.")
            String name,
            @NotNull(message = "성별은 필수입니다.")
            String gender,
            @NotNull(message = "이메일은 필수입니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,

            @NotBlank(message = "전화번호는 필수입니다.")
            String phoneNumber,

            @NotBlank(message = "생일은 필수입니다.")
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "생일 형식이 올바르지 않습니다. (YYYY-MM-DD)")
            String birthday_Date,

            String address
    ) {}

    public record Login(String email, String password) {
    }

    // 미션1: 진행중인 미션 조회 요청
    public record MissionPageRequest(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId
    ) {
    }

    @Builder
    public record MissionPageResponse(
            List<MissionInfo> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionInfo(
            Long missionId,
            String storeName,
            Integer reward,
            String missionSpec,
            LocalDate deadline,
            String status
    ) {}


}
