package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId")
    private Location location;

    @Column(name = "storeName")
    private String storeName;

    @Column(name = "managerNumber")
    private Long managerNumber;

    @Column(name = "detailedLocation")
    private String detailedLocation;

    @OneToMany(mappedBy = "store")
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
