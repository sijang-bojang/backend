package com.sijangmission.demo.domain.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "markets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Market {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long marketId;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "latitude")
    private String latitude;
    
    @Column(name = "longitude")
    private String longitude;
    
    @Column(name = "description")
    private String description;
    
    // One-to-Many relationship with Course
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();
    
    // One-to-Many relationship with Spot
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Spot> spots = new ArrayList<>();
}
