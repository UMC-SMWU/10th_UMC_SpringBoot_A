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

    @JsonInclude(JsonInclude.Include.NON_NULL)  // null이면 응답에서 제외
    private final T result;

    // 성공 응답
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