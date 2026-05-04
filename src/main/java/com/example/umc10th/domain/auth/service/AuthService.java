package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
<<<<<<< HEAD
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
=======
>>>>>>> upstream/Kim-DongH
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

<<<<<<< HEAD
    private final MemberRepository memberRepository;

    public AuthResDTO.SignUpResult signUp(AuthReqDTO.SignUp dto)
    {
        if (memberRepository.existsByEmailAndDeletedAtIsNull(dto.email())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .email(dto.email())
                .password(dto.password())
                .build();

        Member savedMember = memberRepository.save(member);

        return new AuthResDTO.SignUpResult(
                savedMember.getId(),
                savedMember.getEmail()
        );
=======
    public AuthResDTO.SignUpResult signUp(AuthReqDTO.SignUp dto) {
        return null;
>>>>>>> upstream/Kim-DongH
    }
}
