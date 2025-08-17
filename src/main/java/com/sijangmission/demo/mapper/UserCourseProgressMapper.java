package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.relation.UserCourseProgress;
import com.sijangmission.demo.dto.UserCourseProgressDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCourseProgressMapper {
    
    public UserCourseProgressDto toDto(UserCourseProgress userCourseProgress) {
        if (userCourseProgress == null) {
            return null;
        }
        
        return UserCourseProgressDto.builder()
                .id(userCourseProgress.getId())
                .userId(userCourseProgress.getUser() != null ? userCourseProgress.getUser().getUserId() : null)
                .username(userCourseProgress.getUser() != null ? userCourseProgress.getUser().getUsername() : null)
                .courseId(userCourseProgress.getCourse() != null ? userCourseProgress.getCourse().getCourseId() : null)
                .courseName(userCourseProgress.getCourse() != null ? userCourseProgress.getCourse().getName() : null)
                .marketName(userCourseProgress.getCourse() != null && userCourseProgress.getCourse().getMarket() != null ? 
                    userCourseProgress.getCourse().getMarket().getName() : null)
                .currentStep(userCourseProgress.getCurrentStep())
                .totalSteps(userCourseProgress.getCourse() != null && userCourseProgress.getCourse().getCourseSpots() != null ? 
                    userCourseProgress.getCourse().getCourseSpots().size() : 0)
                .status(userCourseProgress.getStatus())
                .startedAt(userCourseProgress.getStartedAt())
                .completedAt(userCourseProgress.getCompletedAt())
                .build();
    }
    
    public List<UserCourseProgressDto> toDtoList(List<UserCourseProgress> userCourseProgresses) {
        if (userCourseProgresses == null) {
            return null;
        }
        
        return userCourseProgresses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
