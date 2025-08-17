package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.core.User;
import com.sijangmission.demo.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        
        long completedMissionCount = user.getUserMissions() != null ? 
            user.getUserMissions().stream()
                .filter(userMission -> "COMPLETED".equals(userMission.getStatus()))
                .count() : 0;
                
        long completedCourseCount = user.getUserCourseProgresses() != null ? 
            user.getUserCourseProgresses().stream()
                .filter(userCourseProgress -> "COMPLETED".equals(userCourseProgress.getStatus()))
                .count() : 0;
        
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .totalReward(user.getTotalReward())
                .exp(user.getExp())
                .completedMissionCount((int) completedMissionCount)
                .completedCourseCount((int) completedCourseCount)
                .build();
    }
}
