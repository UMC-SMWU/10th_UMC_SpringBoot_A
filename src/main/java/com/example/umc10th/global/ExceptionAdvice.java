package com.example.umc10th.global;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    // 우리가 만든 커스텀 예외 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(GeneralException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.onFailure(errorCode));
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception e) {
        return ResponseEntity
                .status(500)
                .body(ApiResponse.<Object>builder()
                        .isSuccess(false)
                        .code("COMMON_500")
                        .message("서버 오류가 발생했습니다.")
                        .result(null)
                        .build());
    }
}