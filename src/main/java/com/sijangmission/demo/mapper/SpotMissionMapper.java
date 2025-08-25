package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.relation.SpotMission;
import com.sijangmission.demo.dto.SpotMissionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotMissionMapper {
    
    public SpotMissionDto toDto(SpotMission entity) {
        if (entity == null) {
            return null;
        }
        
        return SpotMissionDto.builder()
                .id(entity.getId())
                .spotId(entity.getSpot() != null ? entity.getSpot().getSpotId() : null)
                .spotName(entity.getSpot() != null ? entity.getSpot().getName() : null)
                .missionId(entity.getMission() != null ? entity.getMission().getMissionId() : null)
                .missionTitle(entity.getMission() != null ? entity.getMission().getTitle() : null)
                .missionDescription(entity.getMission() != null ? entity.getMission().getDescription() : null)
                .missionType(entity.getMission() != null ? entity.getMission().getMissionType() : null)
                .rewardPoints(entity.getMission() != null ? entity.getMission().getRewardPoints() : null)
                .build();
    }
    
    public List<SpotMissionDto> toDtoList(List<SpotMission> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public SpotMission toEntity(SpotMissionDto dto) {
        if (dto == null) {
            return null;
        }
        
        SpotMission entity = new SpotMission();
        entity.setId(dto.getId());
        // Spot과 Mission은 별도로 설정해야 함
        return entity;
    }
}
