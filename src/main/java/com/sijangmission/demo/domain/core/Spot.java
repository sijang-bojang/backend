package com.sijangmission.demo.domain.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    private Long spotId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private com.sijangmission.demo.domain.core.Image image;
    
    @Column(name = "latitude")
    private String latitude;
    
    @Column(name = "longitude")
    private String longitude;
    
    // Many-to-Many relationship with Course through CourseSpot
    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.CourseSpot> courseSpots = new ArrayList<>();
    
    // Many-to-Many relationship with Mission through SpotMission
    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.SpotMission> spotMissions = new ArrayList<>();
}
