package com.example.umc10th.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    // 실패 응답
    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode) {
        return new ApiResponse<>(false
                ,errorCode.getCode()
                ,errorCode.getMessage()
                ,null);

    }

    // 성공 응답 (코드 없이 기본값)
    public static <T> ApiResponse<T> onSuccess(T result) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code("COMMON_200")
                .message("요청이 성공했습니다.")
                .result(result)
                .build();
    }

    // 성공 응답 (SuccessCode 사용)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode successCode, T result) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code(successCode.getCode())
                .message(successCode.getMessage())
                .result(result)
                .build();
    }

}