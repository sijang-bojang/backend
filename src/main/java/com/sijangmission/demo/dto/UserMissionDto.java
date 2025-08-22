package com.sijangmission.demo.dto;

import com.sijangmission.demo.domain.relation.UserMission;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionDto {
    
    private Long userMissionId;
    private Long userId;
    private String userName;
    private Long missionId;
    private String missionTitle;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private boolean completed;
    private boolean inProgress;
    
    // UserMission 엔티티에서 DTO로 변환하는 정적 메서드
    public static UserMissionDto fromEntity(UserMission userMission) {
        UserMissionDto dto = new UserMissionDto();
        dto.setUserMissionId(userMission.getUserMissionId());
        
        // User 정보 (LAZY 로딩이므로 null 체크)
        try {
            if (userMission.getUser() != null) {
                dto.setUserId(userMission.getUser().getUserId());
                dto.setUserName(userMission.getUser().getUsername());
            } else {
                System.err.println("User is null in UserMission: " + userMission.getUserMissionId());
            }
        } catch (Exception e) {
            // LAZY 로딩 실패 시 기본값 설정
            System.err.println("Failed to load user data: " + e.getMessage());
        }
        
        // Mission 정보 (LAZY 로딩이므로 null 체크)
        try {
            if (userMission.getMission() != null) {
                dto.setMissionId(userMission.getMission().getMissionId());
                dto.setMissionTitle(userMission.getMission().getTitle());
            } else {
                System.err.println("Mission is null in UserMission: " + userMission.getUserMissionId());
            }
        } catch (Exception e) {
            // LAZY 로딩 실패 시 기본값 설정
            System.err.println("Failed to load mission data: " + e.getMessage());
        }
        
        dto.setStatus(userMission.getStatus());
        dto.setStartedAt(userMission.getStartedAt());
        dto.setCompletedAt(userMission.getCompletedAt());
        dto.setCompleted(userMission.isCompleted());
        dto.setInProgress(userMission.isInProgress());
        
        return dto;
    }
}
