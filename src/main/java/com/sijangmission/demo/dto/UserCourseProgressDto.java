package com.sijangmission.demo.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseProgressDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long courseId;
    private String courseName;
    private Integer currentStep;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private Double progressPercentage;
}
