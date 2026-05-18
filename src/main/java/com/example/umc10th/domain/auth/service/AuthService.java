package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.dto.request.SignupReqDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupReqDTO request) {

        User user = User.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .email(request.getEmail())

                // BCrypt 암호화
                .password(passwordEncoder.encode(request.getPassword()))

                .phoneNumber(request.getPhoneNumber())

                // 기본값
                .point(0)
                .phoneVerified(false)

                .createdAt(LocalDateTime.now().now())
                .updatedAt(LocalDateTime.now().now())

                .build();

        userRepository.save(user);
    }
}