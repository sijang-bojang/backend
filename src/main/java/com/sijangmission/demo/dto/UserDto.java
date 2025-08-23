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
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private Integer totalReward;
    private Integer exp;
    private Integer completedMissionCount;
    private Integer completedCourseCount;
    private Integer completeStamp;
}
