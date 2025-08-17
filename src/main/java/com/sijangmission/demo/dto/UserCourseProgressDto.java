package com.sijangmission.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCourseProgressDto {
    private Long id;
    private Long userId;
    private String username;
    private Long courseId;
    private String courseName;
    private String marketName;
    private Integer currentStep;
    private Integer totalSteps;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
}
