package com.sijangmission.demo.domain.relation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "spot_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpotType {
    
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
