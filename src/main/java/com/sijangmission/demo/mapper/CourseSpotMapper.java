package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.relation.CourseSpot;
import com.sijangmission.demo.dto.CourseSpotDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseSpotMapper {
    
    public CourseSpotDto toDto(CourseSpot courseSpot) {
        if (courseSpot == null) {
            return null;
        }
        
        return CourseSpotDto.builder()
                .spotId(courseSpot.getSpot() != null ? courseSpot.getSpot().getSpotId() : null)
                .spotName(courseSpot.getSpot() != null ? courseSpot.getSpot().getName() : null)
                .category(courseSpot.getSpot() != null ? courseSpot.getSpot().getCategory() : null)
                .description(courseSpot.getSpot() != null ? courseSpot.getSpot().getDescription() : null)
                .latitude(courseSpot.getSpot() != null ? courseSpot.getSpot().getLatitude() : null)
                .longitude(courseSpot.getSpot() != null ? courseSpot.getSpot().getLongitude() : null)
                .stepNumber(courseSpot.getStepNumber())
                .build();
    }
    
    public List<CourseSpotDto> toDtoList(List<CourseSpot> courseSpots) {
        if (courseSpots == null) {
            return null;
        }
        
        return courseSpots.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
