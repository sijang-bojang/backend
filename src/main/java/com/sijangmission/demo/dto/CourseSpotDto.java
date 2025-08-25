package com.sijangmission.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseSpotDto {
    private Long spotId;
    private String spotName;
    private String category;
    private String description;
    private Double latitude;
    private Double longitude;
    private Integer stepNumber;
}
