package com.sijangmission.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "missions")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    private String title;
    private String description;
    private Integer rewardPoints;
    private String missionType;

    @OneToMany(mappedBy = "mission")
    private List<SpotMission> spotMissions = new ArrayList<>();

    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions = new ArrayList<>();
}
