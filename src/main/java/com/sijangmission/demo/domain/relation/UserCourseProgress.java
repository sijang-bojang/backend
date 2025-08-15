package com.sijangmission.demo.domain.relation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_course_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseProgress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private com.sijangmission.demo.domain.core.User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private com.sijangmission.demo.domain.core.Course course;
    
    @Column(name = "current_step")
    private Integer currentStep;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "started_at")
    private LocalDateTime startedAt;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
