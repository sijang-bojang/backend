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
    
    // Many-to-Many relationship with Type through CourseType
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.CourseType> courseTypes = new ArrayList<>();
    
    // 편의 메서드: 코스의 타입들 조회
    public List<Type> getTypes() {
        return courseTypes.stream()
                .map(courseType -> courseType.getType())
                .toList();
    }
    
    // 편의 메서드: 특정 타입인지 확인
    public boolean hasType(String typeName) {
        return courseTypes.stream()
                .anyMatch(courseType -> courseType.getType().getName().equals(typeName));
    }
    
    // 편의 메서드: 가족 코스인지 확인
    public boolean isFamilyCourse() {
        return hasType("가족이랑 가기 좋은 코스");
    }
    
    // 편의 메서드: 연인 코스인지 확인
    public boolean isCoupleCourse() {
        return hasType("연인과 가기 좋은 코스");
    }
    
    // One-to-Many relationship with UserCourseProgress
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.UserCourseProgress> userCourseProgresses = new ArrayList<>();
}
