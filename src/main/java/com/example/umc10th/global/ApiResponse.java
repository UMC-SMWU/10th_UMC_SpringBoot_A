package com.example.umc10th.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {

    private final boolean isSuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

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

    // 실패 응답
    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .isSuccess(false)
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .result(null)
                .build();
    }
}