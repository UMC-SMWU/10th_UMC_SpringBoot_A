package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name="birth", nullable = false)
    private LocalDate birth;

    @Column(name="address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name="detail_address", nullable = false)
    private String detailAddress;

    @Column (name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="profile_url")
    private String profileUrl;

    @Column(name="point")
    @Builder.Default
    private Integer point = 0;


    // 연관 관계
    @Builder.Default
    @OneToMany(mappedBy="member")
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy="member")
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
