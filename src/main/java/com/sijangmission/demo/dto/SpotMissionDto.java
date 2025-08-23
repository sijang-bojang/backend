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
public class SpotMissionDto {
    private Long id;
    private Long spotId;
    private String spotName;
    private Long missionId;
    private String missionTitle;
    private String missionDescription;
    private String missionType;
    private Integer rewardPoints;
}
