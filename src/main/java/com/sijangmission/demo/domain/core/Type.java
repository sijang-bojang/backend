package com.sijangmission.demo.domain.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    // Many-to-Many relationship with Course through CourseType
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.CourseType> courseTypes = new ArrayList<>();
    
    // 편의 메서드: 타입에 속한 코스들 조회
    public List<Course> getCourses() {
        return courseTypes.stream()
                .map(courseType -> courseType.getCourse())
                .toList();
    }
    
    // 편의 메서드: 가족 코스 타입인지 확인
    public boolean isFamilyType() {
        return "가족이랑 가기 좋은 코스".equals(this.name);
    }
    
    // 편의 메서드: 연인 코스 타입인지 확인
    public boolean isCoupleType() {
        return "연인과 가기 좋은 코스".equals(this.name);
    }
}
