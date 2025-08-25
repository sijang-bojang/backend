package com.sijangmission.demo.domain.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "total_reward")
    private Integer totalReward = 0;
    
    @Column(name = "exp")
    private Integer exp = 0;
    
    @Column(name = "complete_stamp")
    private Integer completeStamp = 0;
    
    // Many-to-Many relationship with Mission through UserMission
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.UserMission> userMissions = new ArrayList<>();
    
    // Many-to-Many relationship with Course through UserCourseProgress
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.UserCourseProgress> userCourseProgresses = new ArrayList<>();
    
    // 편의 메서드: 사용자의 미션들 조회
    public List<Mission> getMissions() {
        return userMissions.stream()
                .map(userMission -> userMission.getMission())
                .toList();
    }
    
    // 편의 메서드: 사용자의 코스들 조회
    public List<Course> getCourses() {
        return userCourseProgresses.stream()
                .map(userCourseProgress -> userCourseProgress.getCourse())
                .toList();
    }
    
    // 편의 메서드: 보상 포인트 추가
    public void addRewardPoints(Integer points) {
        this.totalReward += points;
    }
    
    // 편의 메서드: 경험치 추가
    public void addExperience(Integer exp) {
        this.exp += exp;
    }
    
    // 편의 메서드: 완료된 스탬프 추가
    public void addCompleteStamp() {
        this.completeStamp += 1;
    }
    
    // 편의 메서드: 완료된 스탬프 개수 조회
    public Integer getCompleteStampCount() {
        return this.completeStamp;
    }
}
