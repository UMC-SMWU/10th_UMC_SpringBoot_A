package com.example.umc10th.global.apiPayload.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // @Valid 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .status(400)
                .body(ApiResponse.<Map<String, String>>builder()
                        .isSuccess(false)
                        .code("COMMON400")
                        .message("잘못된 요청입니다.")
                        .result(errors)
                        .build());
    }

    // 비즈니스 예외 (ProjectException) - 에러코드 그대로 응답
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(ProjectException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.onFailure(errorCode));
    }

    // 그 외 서버 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception e) {
        return ResponseEntity
                .status(500)
                .body(ApiResponse.<Void>builder()
                        .isSuccess(false)
                        .code("COMMON5001")
                        .message("서버 에러가 발생했습니다.")
                        .result(null)
                        .build());
    }
}