package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
<<<<<<< HEAD
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
=======
import com.example.umc10th.domain.member.dto.MemberResDTO.GetInfo;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ){
        return GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}
>>>>>>> upstream/Kim-DongH
