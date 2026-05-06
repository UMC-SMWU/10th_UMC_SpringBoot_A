package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Address name = Address.NONE;

    @Builder.Default
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();
}
