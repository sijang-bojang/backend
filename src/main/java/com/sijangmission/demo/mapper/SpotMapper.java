package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.core.Spot;
import com.sijangmission.demo.dto.SpotDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotMapper {
    
    public SpotDto toDto(Spot spot) {
        if (spot == null) {
            return null;
        }
        
        // VISIT 타입 미션만 필터링
        List<String> visitMissionTitles = spot.getMissions() != null ? 
            spot.getMissions().stream()
                .filter(mission -> "VISIT".equals(mission.getMissionType()))
                .map(mission -> mission.getTitle())
                .collect(Collectors.toList()) : null;
        
        return SpotDto.builder()
                .spotId(spot.getSpotId())
                .marketId(spot.getMarket() != null ? spot.getMarket().getMarketId() : null)
                .marketName(spot.getMarket() != null ? spot.getMarket().getName() : null)
                .name(spot.getName())
                .category(spot.getCategory())
                .description(spot.getDescription())
                .imageUrl(spot.getImage() != null ? spot.getImage().getImageUrl() : null)
                .latitude(spot.getLatitude())
                .longitude(spot.getLongitude())
                .missionCount(spot.getMissions() != null ? spot.getMissions().size() : 0)
                .visitMissionTitles(visitMissionTitles)
                .courseNames(spot.getCourses() != null ? 
                    spot.getCourses().stream()
                        .map(course -> course.getName())
                        .collect(Collectors.toList()) : null)
                .build();
    }
    
    public List<SpotDto> toDtoList(List<Spot> spots) {
        if (spots == null) {
            return null;
        }
        
        return spots.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
