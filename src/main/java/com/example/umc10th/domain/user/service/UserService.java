package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    // 마이페이지 조회
    public UserResDTO.GetInfo getMyPage(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        return UserResDTO.GetInfo.builder()
                .name(user.getName())
                .profileUrl("https://example.com/profile.jpg") // 임시
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();
    }

    // 마이페이지 수정
    @Transactional
    public UserResDTO.GetInfo updateMyPage(Long userId, UserReqDTO.UpdateInfo request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        User updatedUser = User.builder()
                .id(user.getId())
                .name(request.name())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .point(user.getPoint())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .address(user.getAddress())
                .addressDetail(user.getAddressDetail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .phoneVerified(user.getPhoneVerified())
                .build();

        userRepository.save(updatedUser);

        return UserResDTO.GetInfo.builder()
                .name(updatedUser.getName())
                .profileUrl("https://example.com/profile.jpg")
                .email(updatedUser.getEmail())
                .phoneNumber(updatedUser.getPhoneNumber())
                .point(updatedUser.getPoint())
                .build();
    }

    // 홈 화면 - 현재 지역 기반 미션 조회 (페이징)
    public Page<Mission> getHomeMissions(String region, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        return missionRepository.findAvailableMissions(region, pageable);
    }
}