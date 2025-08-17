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
public class UserMissionDto {
    private Long userMissionId;
    private Long userId;
    private String username;
    private Long missionId;
    private String missionTitle;
    private String missionType;
    private Integer rewardPoints;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
}
