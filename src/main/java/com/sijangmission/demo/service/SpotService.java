package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.Spot;
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
    
    public List<Spot> getAllSpots() {
        return spotRepository.findAll();
    }
    
    public Optional<Spot> getSpotById(Long spotId) {
        return spotRepository.findById(spotId);
    }
    
    public List<Spot> getSpotsByMarketId(Long marketId) {
        return spotRepository.findByMarketMarketId(marketId);
    }
    
    public List<Spot> getSpotsByCategory(String category) {
        return spotRepository.findByCategory(category);
    }
    
    public List<Spot> getSpotsByName(String name) {
        return spotRepository.findByNameContaining(name);
    }
    
    public List<Spot> getSpotsByCategoryAndMarketId(String category, Long marketId) {
        return spotRepository.findByCategoryAndMarketMarketId(category, marketId);
    }
    
    @Transactional
    public Spot saveSpot(Spot spot) {
        return spotRepository.save(spot);
    }
    
    @Transactional
    public void deleteSpot(Long spotId) {
        spotRepository.deleteById(spotId);
    }
}
