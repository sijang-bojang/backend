package com.sijangmission.demo.domain.relation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_mission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long userMissionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private com.sijangmission.demo.domain.core.User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private com.sijangmission.demo.domain.core.Mission mission;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "started_at")
    private LocalDateTime startedAt;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    // 편의 메서드: 미션 시작
    public void startMission() {
        this.status = "IN_PROGRESS";
        this.startedAt = LocalDateTime.now();
    }
    
    // 편의 메서드: 미션 완료
    public void completeMission() {
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
