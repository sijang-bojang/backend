package com.sijangmission.demo.domain.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "reward_points")
    private Integer rewardPoints;
    
    @Column(name = "mission_type")
    private String missionType;
    
    // Many-to-Many relationship with Spot through SpotMission
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.SpotMission> spotMissions = new ArrayList<>();
    
    // Many-to-Many relationship with User through UserMission
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.UserMission> userMissions = new ArrayList<>();
}
