package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.member.entity.Food;
<<<<<<< HEAD
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
=======
>>>>>>> upstream/Kim-DongH
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
<<<<<<< HEAD
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
=======
import jakarta.persistence.Table;
import java.math.BigInteger;
>>>>>>> upstream/Kim-DongH
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

<<<<<<< HEAD
    @Column(name = "store_description")
    private String storeDescription;

=======
>>>>>>> upstream/Kim-DongH
    @Column(name = "managerNumber")
    private Long managerNumber;

    @Column(name = "detailedLocation")
    private String detailedLocation;
<<<<<<< HEAD

    @OneToMany(mappedBy = "store")
    private List<MemberMission> memberMissionList = new ArrayList<>();
=======
>>>>>>> upstream/Kim-DongH
}
