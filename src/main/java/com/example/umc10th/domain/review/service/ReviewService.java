package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public ReviewResDTO.CreateResultDTO write(Long missionId, ReviewReqDTO.CreateDTO request) {
        return ReviewResDTO.CreateResultDTO.builder()
                .reviewId(1L)
                .rate("⭐⭐⭐⭐⭐️")
                .content("content")
                .imageUrls(List.of("https://image1.png", "https://image2.png"))
                .build();

}}