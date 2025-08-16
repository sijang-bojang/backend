package com.sijangmission.demo.domain.relation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "spot_types") // 테이블 이름은 기존과 동일하게 유지
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private com.sijangmission.demo.domain.core.Course course;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private com.sijangmission.demo.domain.core.Type type;
}
