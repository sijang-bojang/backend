package com.sijangmission.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Long courseId;
    private Long marketId;
    private String marketName;
    private String name;
    private String description;
    private List<String> typeNames;
    private Integer spotCount;
    private List<CourseSpotDto> courseSpots;
    private Boolean isFamilyCourse;
    private Boolean isCoupleCourse;
}
