package com.example.umc10th.domain.mission.entity;

<<<<<<< HEAD
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
=======
>>>>>>> upstream/Kim-DongH
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
=======
import jakarta.persistence.Table;
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
<<<<<<< HEAD
@Table(name = "location")
=======
@Table(name = "locaton")
>>>>>>> upstream/Kim-DongH
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationId")
    private Long id;

    @Column(name = "locationName")
    private String locationName;
<<<<<<< HEAD

    @OneToMany(mappedBy = "location")
    private List<MemberMission> memberMissionList = new ArrayList<>();
=======
>>>>>>> upstream/Kim-DongH
}
