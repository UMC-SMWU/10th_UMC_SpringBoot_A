package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public static class CreateDTO {
        private Double rate;
        private String content;
        private List<String> imageUrls;
    }
}