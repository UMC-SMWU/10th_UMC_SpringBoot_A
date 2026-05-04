package com.example.umc10th.domain.mission.entity;

<<<<<<< HEAD
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
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
import java.time.LocalDate;
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
@Table(name = "mission")
<<<<<<< HEAD
public class Mission extends BaseEntity{
=======
public class Mission {
>>>>>>> upstream/Kim-DongH

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missionId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId")
    private Location location;


    @Column(name = "reward")
<<<<<<< HEAD
    private Integer reward;

    @Column(name = "verificationCode")
    private String verificationCode;
=======
    private String reward;

    @Column(name = "vertificationCode")
    private String vertificationCode;
>>>>>>> upstream/Kim-DongH

    @Column(name = "missionContent")
    private String missionContent;

<<<<<<< HEAD
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissionList = new ArrayList<>();
=======
    @Column(name = "created_at")
    private LocalDate created_at;

    @Column(name = "updated_at")
    private LocalDate updated_at;

    @Column(name = "deleted_at")
    private LocalDate deleted_at;
>>>>>>> upstream/Kim-DongH
}
