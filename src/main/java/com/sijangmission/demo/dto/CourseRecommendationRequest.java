package com.sijangmission.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class CourseRecommendationRequest {
    private Long marketId;
    private String marketName;
    private List<String> tags;
}
