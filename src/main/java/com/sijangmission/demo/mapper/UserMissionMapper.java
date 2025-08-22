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
        
        return UserMissionDto.fromEntity(userMission);
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
