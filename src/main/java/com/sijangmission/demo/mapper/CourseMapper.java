package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.core.Course;
import com.sijangmission.demo.dto.CourseDto;
import com.sijangmission.demo.dto.CourseSpotDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {
    
    public CourseDto toDto(Course course) {
        if (course == null) {
            return null;
        }
        
        return CourseDto.builder()
                .courseId(course.getCourseId())
                .marketId(course.getMarket() != null ? course.getMarket().getMarketId() : null)
                .marketName(course.getMarket() != null ? course.getMarket().getName() : null)
                .name(course.getName())
                .description(course.getDescription())
                .typeNames(course.getTypes() != null ? 
                    course.getTypes().stream()
                        .map(type -> type.getName())
                        .collect(Collectors.toList()) : null)
                .spotCount(course.getCourseSpots() != null ? course.getCourseSpots().size() : 0)
                .courseSpots(course.getCourseSpots() != null ? 
                    course.getCourseSpots().stream()
                        .map(courseSpot -> CourseSpotDto.builder()
                                .spotId(courseSpot.getSpot().getSpotId())
                                .spotName(courseSpot.getSpot().getName())
                                .category(courseSpot.getSpot().getCategory())
                                .description(courseSpot.getSpot().getDescription())
                                .latitude(courseSpot.getSpot().getLatitude())
                                .longitude(courseSpot.getSpot().getLongitude())
                                .stepNumber(courseSpot.getStepNumber())
                                .build())
                        .collect(Collectors.toList()) : null)
                .isFamilyCourse(course.isFamilyCourse())
                .isCoupleCourse(course.isCoupleCourse())
                .build();
    }
    
    public List<CourseDto> toDtoList(List<Course> courses) {
        if (courses == null) {
            return null;
        }
        
        return courses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
