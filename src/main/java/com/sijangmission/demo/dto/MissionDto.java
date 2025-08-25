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
public class MissionDto {
    private Long missionId;
    private String title;
    private String description;
    private Integer rewardPoints;
    private String missionType;
    private List<String> spotNames;
    private Boolean isVisitType;
    private Boolean isNonVisitType;
}
