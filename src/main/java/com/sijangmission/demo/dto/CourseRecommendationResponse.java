package com.sijangmission.demo.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRecommendationResponse {
    private Long courseId;
    private String courseName;
    private String description;
    private String marketName;
    private String recommendationReason;
    private Double confidenceScore;
}
