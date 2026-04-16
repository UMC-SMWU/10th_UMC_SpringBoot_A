package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "profileUrl")
    private String profileUrl;

    @Column(name = "point")
    private Integer point;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "isPhoneVerified")
    private Boolean isPhoneVerified;

    @Column(name = "notificationEnabled")
    private Boolean notificationEnabled;

    @Column(name = "socialUid")
    private String socialUid;

    @Column(name = "socialType")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "updatedAt")
    private LocalDate updatedAt;

    @Column(name = "deletedAt")
    private LocalDate deletedAt;

}
