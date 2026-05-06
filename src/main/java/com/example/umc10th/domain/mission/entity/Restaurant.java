package com.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "restaurant_name", nullable = false, length = 100)
    private String name;

    @Column(name = "star_rating", nullable = false)
    private Float starRating;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "restaurant_type", nullable = false, length = 50)
    private String type;
}