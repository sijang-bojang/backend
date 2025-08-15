package com.sijangmission.demo.domain.relation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "course_spots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseSpot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private com.sijangmission.demo.domain.core.Course course;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private com.sijangmission.demo.domain.core.Spot spot;
    
    @Column(name = "step_number")
    private Integer stepNumber;
}
