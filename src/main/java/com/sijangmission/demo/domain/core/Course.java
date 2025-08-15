package com.sijangmission.demo.domain.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    // Many-to-Many relationship with Spot through CourseSpot
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.CourseSpot> courseSpots = new ArrayList<>();
    
    // Many-to-Many relationship with Type through SpotType
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.SpotType> spotTypes = new ArrayList<>();
    
    // One-to-Many relationship with UserCourseProgress
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.UserCourseProgress> userCourseProgresses = new ArrayList<>();
}
