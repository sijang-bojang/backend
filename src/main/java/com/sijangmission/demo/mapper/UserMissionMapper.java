package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.relation.UserMission;
import com.sijangmission.demo.dto.UserMissionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMissionMapper {
    
    public UserMissionDto toDto(UserMission userMission) {
        if (userMission == null) {
            return null;
        }
        
        UserMissionDto dto = new UserMissionDto();
        dto.setUserMissionId(userMission.getUserMissionId());
        
        // User 정보
        if (userMission.getUser() != null) {
            dto.setUserId(userMission.getUser().getUserId());
            dto.setUserName(userMission.getUser().getUsername());
        }
        
        // Mission 정보
        if (userMission.getMission() != null) {
            dto.setMissionId(userMission.getMission().getMissionId());
            dto.setMissionTitle(userMission.getMission().getTitle());
        }
        
        dto.setStatus(userMission.getStatus());
        dto.setStartedAt(userMission.getStartedAt());
        dto.setCompletedAt(userMission.getCompletedAt());
        dto.setCompleted(userMission.isCompleted());
        dto.setInProgress(userMission.isInProgress());
        
        return dto;
    }
    
    public List<UserMissionDto> toDtoList(List<UserMission> userMissions) {
        if (userMissions == null) {
            return null;
        }
        
        return userMissions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
