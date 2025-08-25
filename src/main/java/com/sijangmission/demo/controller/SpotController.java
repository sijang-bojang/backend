package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.core.Spot;
import com.sijangmission.demo.dto.SpotDto;
import com.sijangmission.demo.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotController {
    
    private final SpotService spotService;
    
    @GetMapping
    public ResponseEntity<List<SpotDto>> getAllSpots() {
        List<SpotDto> spots = spotService.getAllSpots();
        return ResponseEntity.ok(spots);
    }
    
    @GetMapping("/{spotId}")
    public ResponseEntity<SpotDto> getSpotById(@PathVariable Long spotId) {
        Optional<SpotDto> spot = spotService.getSpotById(spotId);
        return spot.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/market/{marketId}")
    public ResponseEntity<List<SpotDto>> getSpotsByMarketId(@PathVariable Long marketId) {
        List<SpotDto> spots = spotService.getSpotsByMarketId(marketId);
        return ResponseEntity.ok(spots);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<SpotDto>> getSpotsByCategory(@PathVariable String category) {
        List<SpotDto> spots = spotService.getSpotsByCategory(category);
        return ResponseEntity.ok(spots);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<SpotDto>> searchSpots(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long marketId) {
        
        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(spotService.getSpotsByName(name));
        } else if (category != null && !category.isEmpty() && marketId != null) {
            return ResponseEntity.ok(spotService.getSpotsByCategoryAndMarketId(category, marketId));
        } else if (category != null && !category.isEmpty()) {
            return ResponseEntity.ok(spotService.getSpotsByCategory(category));
        } else if (marketId != null) {
            return ResponseEntity.ok(spotService.getSpotsByMarketId(marketId));
        } else {
            return ResponseEntity.ok(spotService.getAllSpots());
        }
    }
    
    @PostMapping
    public ResponseEntity<SpotDto> createSpot(@RequestBody Spot spot) {
        SpotDto savedSpot = spotService.saveSpot(spot);
        return ResponseEntity.ok(savedSpot);
    }
    
    @PutMapping("/{spotId}")
    public ResponseEntity<SpotDto> updateSpot(@PathVariable Long spotId, @RequestBody Spot spot) {
        Optional<SpotDto> existingSpot = spotService.getSpotById(spotId);
        if (existingSpot.isPresent()) {
            spot.setSpotId(spotId);
            SpotDto updatedSpot = spotService.saveSpot(spot);
            return ResponseEntity.ok(updatedSpot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{spotId}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long spotId) {
        Optional<SpotDto> spot = spotService.getSpotById(spotId);
        if (spot.isPresent()) {
            spotService.deleteSpot(spotId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
