package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.relation.UserCourseProgress;
import com.sijangmission.demo.dto.UserCourseProgressDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCourseProgressMapper {
    
    public UserCourseProgressDto toDto(UserCourseProgress entity) {
        if (entity == null) {
            return null;
        }
        
        // 진행률 계산 (예시: 5단계 중 3단계 완료 = 60%)
        Double progressPercentage = null;
        if (entity.getCurrentStep() != null) {
            // 실제로는 코스의 총 단계 수를 가져와서 계산해야 함
            // 여기서는 임시로 5단계로 가정
            int totalSteps = 5;
            progressPercentage = (double) entity.getCurrentStep() / totalSteps * 100;
        }
        
        return UserCourseProgressDto.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getUserId() : null)
                .userName(entity.getUser() != null ? entity.getUser().getUsername() : null)
                .courseId(entity.getCourse() != null ? entity.getCourse().getCourseId() : null)
                .courseName(entity.getCourse() != null ? entity.getCourse().getName() : null)
                .currentStep(entity.getCurrentStep())
                .status(entity.getStatus())
                .startedAt(entity.getStartedAt())
                .completedAt(entity.getCompletedAt())
                .progressPercentage(progressPercentage)
                .build();
    }
    
    public List<UserCourseProgressDto> toDtoList(List<UserCourseProgress> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public UserCourseProgress toEntity(UserCourseProgressDto dto) {
        if (dto == null) {
            return null;
        }
        
        UserCourseProgress entity = new UserCourseProgress();
        entity.setId(dto.getId());
        entity.setCurrentStep(dto.getCurrentStep());
        entity.setStatus(dto.getStatus());
        entity.setStartedAt(dto.getStartedAt());
        entity.setCompletedAt(dto.getCompletedAt());
        
        return entity;
    }
}
