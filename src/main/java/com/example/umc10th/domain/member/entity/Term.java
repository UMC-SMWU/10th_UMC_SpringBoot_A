package com.example.umc10th.domain.member.entity;

<<<<<<< HEAD
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.global.entity.BaseEntity;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
=======
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "term")
<<<<<<< HEAD
public class Term extends BaseEntity {
=======
public class Term {
>>>>>>> upstream/Kim-DongH

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "termId")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "context", columnDefinition = "TEXT")
    private String content;

    @Column(name = "isRequired")
    private Boolean isRequired;

<<<<<<< HEAD
    @OneToMany(mappedBy = "term")
    private List<MemberTerm> memberTermList = new ArrayList<>();

=======
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
>>>>>>> upstream/Kim-DongH
}
