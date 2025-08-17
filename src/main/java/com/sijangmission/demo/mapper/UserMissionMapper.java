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
        
        return UserMissionDto.builder()
                .userMissionId(userMission.getUserMissionId())
                .userId(userMission.getUser() != null ? userMission.getUser().getUserId() : null)
                .username(userMission.getUser() != null ? userMission.getUser().getUsername() : null)
                .missionId(userMission.getMission() != null ? userMission.getMission().getMissionId() : null)
                .missionTitle(userMission.getMission() != null ? userMission.getMission().getTitle() : null)
                .missionType(userMission.getMission() != null ? userMission.getMission().getMissionType() : null)
                .rewardPoints(userMission.getMission() != null ? userMission.getMission().getRewardPoints() : null)
                .status(userMission.getStatus())
                .startedAt(userMission.getStartedAt())
                .completedAt(userMission.getCompletedAt())
                .build();
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
