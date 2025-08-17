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
    
    // 편의 메서드: 미션의 스팟들 조회
    public List<Spot> getSpots() {
        return spotMissions.stream()
                .map(spotMission -> spotMission.getSpot())
                .toList();
    }
    
    // 편의 메서드: VISIT 타입인지 확인
    public boolean isVisitType() {
        return "VISIT".equals(this.missionType);
    }
    
    // 편의 메서드: NON_VISIT 타입인지 확인
    public boolean isNonVisitType() {
        return "NON_VISIT".equals(this.missionType);
    }
}
