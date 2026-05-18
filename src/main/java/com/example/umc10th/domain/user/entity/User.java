package com.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(name = "birth", nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private Integer point;

    @Column(length = 50)
    private String address;

    @Column(name = "address_detail", length = 200)
    private String addressDetail;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;

    @Column(name = "phone_number", length = 13)
    private String phoneNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "phone_verified", nullable = false)
    private Boolean phoneVerified;
}