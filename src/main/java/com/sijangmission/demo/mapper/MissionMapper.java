package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.core.Mission;
import com.sijangmission.demo.dto.MissionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionMapper {
    
    public MissionDto toDto(Mission mission) {
        if (mission == null) {
            return null;
        }
        
        return MissionDto.builder()
                .missionId(mission.getMissionId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .rewardPoints(mission.getRewardPoints())
                .missionType(mission.getMissionType())
                .spotNames(mission.getSpots() != null ? 
                    mission.getSpots().stream()
                        .map(spot -> spot.getName())
                        .collect(Collectors.toList()) : null)
                .isVisitType(mission.isVisitType())
                .isNonVisitType(mission.isNonVisitType())
                .build();
    }
    
    public List<MissionDto> toDtoList(List<Mission> missions) {
        if (missions == null) {
            return null;
        }
        
        return missions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
