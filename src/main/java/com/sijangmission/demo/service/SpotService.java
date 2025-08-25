package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Spot;
import com.sijangmission.demo.dto.SpotDto;
import com.sijangmission.demo.mapper.SpotMapper;
import com.sijangmission.demo.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotService {
    
    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;
    
    public List<SpotDto> getAllSpots() {
        List<Spot> spots = spotRepository.findAll();
        return spotMapper.toDtoList(spots);
    }
    
    public Optional<SpotDto> getSpotById(Long spotId) {
        Optional<Spot> spot = spotRepository.findById(spotId);
        return spot.map(spotMapper::toDto);
    }
    
    public List<SpotDto> getSpotsByMarketId(Long marketId) {
        List<Spot> spots = spotRepository.findByMarketMarketId(marketId);
        return spotMapper.toDtoList(spots);
    }
    
    public List<SpotDto> getSpotsByCategory(String category) {
        List<Spot> spots = spotRepository.findByCategory(category);
        return spotMapper.toDtoList(spots);
    }
    
    public List<SpotDto> getSpotsByName(String name) {
        List<Spot> spots = spotRepository.findByNameContaining(name);
        return spotMapper.toDtoList(spots);
    }
    
    public List<SpotDto> getSpotsByCategoryAndMarketId(String category, Long marketId) {
        List<Spot> spots = spotRepository.findByCategoryAndMarketMarketId(category, marketId);
        return spotMapper.toDtoList(spots);
    }
    
    @Transactional
    public SpotDto saveSpot(Spot spot) {
        Spot savedSpot = spotRepository.save(spot);
        return spotMapper.toDto(savedSpot);
    }
    
    @Transactional
    public void deleteSpot(Long spotId) {
        spotRepository.deleteById(spotId);
    }
}
