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
    
    // 편의 메서드: 코스 시작
    public void startCourse() {
        this.status = "IN_PROGRESS";
        this.currentStep = 1;
        this.startedAt = LocalDateTime.now();
    }
    
    // 편의 메서드: 코스 진행 단계 업데이트
    public void updateProgress(Integer step) {
        this.currentStep = step;
    }
    
    // 편의 메서드: 코스 완료
    public void completeCourse() {
        this.status = "COMPLETED";
        this.completedAt = LocalDateTime.now();
    }
    
    // 편의 메서드: 진행 중인지 확인
    public boolean isInProgress() {
        return "IN_PROGRESS".equals(this.status);
    }
    
    // 편의 메서드: 완료되었는지 확인
    public boolean isCompleted() {
        return "COMPLETED".equals(this.status);
    }
}
