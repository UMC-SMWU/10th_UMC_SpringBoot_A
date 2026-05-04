package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO.GetInfo;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    public final MemberRepository memberRepository;

//    @Transactional
//    public String createUser(){
//        Member member = Member.builder()
//                .name("test")
//                .buile();
//        memberRepository.save(member);
//        return "OK";
//    }
//
//    @Transactional
//    public String deleteUser(){
//        memberRepository.deleteByName("test");
//        return "OK";
//    }
//
//    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
//        //dto에서 유저 id 추출
//        Long memberId = dto.id();
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
//        return MemberConverter.toGetInfo(member);
//    }

    public MemberResDTO.MyPage getMyPage() {
        return null;
    }

    public MemberResDTO.HomeInfo getHome() {
        return null;
    }
}
