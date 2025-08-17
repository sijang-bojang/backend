package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.core.Market;
import com.sijangmission.demo.dto.MarketDto;
import com.sijangmission.demo.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/markets")
@RequiredArgsConstructor
public class MarketController {
    
    private final MarketService marketService;
    
    @GetMapping
    public ResponseEntity<List<MarketDto>> getAllMarkets() {
        List<MarketDto> markets = marketService.getAllMarkets();
        return ResponseEntity.ok(markets);
    }
    
    @GetMapping("/{marketId}")
    public ResponseEntity<MarketDto> getMarketById(@PathVariable Long marketId) {
        Optional<MarketDto> market = marketService.getMarketById(marketId);
        return market.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MarketDto>> searchMarkets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {
        
        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(marketService.getMarketsByName(name));
        } else if (address != null && !address.isEmpty()) {
            return ResponseEntity.ok(marketService.getMarketsByAddress(address));
        } else {
            return ResponseEntity.ok(marketService.getAllMarkets());
        }
    }
    
    @PostMapping
    public ResponseEntity<MarketDto> createMarket(@RequestBody Market market) {
        MarketDto savedMarket = marketService.saveMarket(market);
        return ResponseEntity.ok(savedMarket);
    }
    
    @PutMapping("/{marketId}")
    public ResponseEntity<MarketDto> updateMarket(@PathVariable Long marketId, @RequestBody Market market) {
        Optional<MarketDto> existingMarket = marketService.getMarketById(marketId);
        if (existingMarket.isPresent()) {
            market.setMarketId(marketId);
            MarketDto updatedMarket = marketService.saveMarket(market);
            return ResponseEntity.ok(updatedMarket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{marketId}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long marketId) {
        Optional<MarketDto> market = marketService.getMarketById(marketId);
        if (market.isPresent()) {
            marketService.deleteMarket(marketId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
