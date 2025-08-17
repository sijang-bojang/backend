package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.core.Market;
import com.sijangmission.demo.dto.MarketDto;
import org.springframework.stereotype.Component;

@Component
public class MarketMapper {
    
    public MarketDto toDto(Market market) {
        if (market == null) {
            return null;
        }
        
        return MarketDto.builder()
                .marketId(market.getMarketId())
                .name(market.getName())
                .address(market.getAddress())
                .latitude(market.getLatitude())
                .longitude(market.getLongitude())
                .description(market.getDescription())
                .courseCount(market.getCourses() != null ? market.getCourses().size() : 0)
                .spotCount(market.getSpots() != null ? market.getSpots().size() : 0)
                .build();
    }
}
