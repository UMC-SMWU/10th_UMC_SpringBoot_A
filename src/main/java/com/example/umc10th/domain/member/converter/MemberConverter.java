package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import java.util.List;

public class MemberConverter {
    public static MemberResDTO.MyPage toGetInfo(Member member){
        return new MemberResDTO.MyPage(
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getIsPhoneVerified(),
                member.getPoint(),
                member.getNotificationEnabled(),
                List.of()
        );
    }
}