package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public ReviewResDTO.CreateResultDTO write(Long missionId, ReviewReqDTO.CreateDTO request) {
        //실제 로직 구현
        return null;
    }
}