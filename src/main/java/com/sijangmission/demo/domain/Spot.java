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
@Table(name = "spots")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    private String name;
    private String category;
    private String description;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "spot")
    private List<CourseSpot> courseSpots = new ArrayList<>();

    @OneToMany(mappedBy = "spot")
    private List<SpotMission> spotMissions = new ArrayList<>();
}
