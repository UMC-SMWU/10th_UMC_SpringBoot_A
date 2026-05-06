package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.enums.FoodName;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @Enumerated(EnumType.STRING)
    private FoodName name;

    // 연관 관계
    @Builder.Default
    @OneToMany(mappedBy="food")
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
