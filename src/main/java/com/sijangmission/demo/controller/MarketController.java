package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.core.Market;
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
    public ResponseEntity<List<Market>> getAllMarkets() {
        List<Market> markets = marketService.getAllMarkets();
        return ResponseEntity.ok(markets);
    }
    
    @GetMapping("/{marketId}")
    public ResponseEntity<Market> getMarketById(@PathVariable Long marketId) {
        Optional<Market> market = marketService.getMarketById(marketId);
        return market.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Market>> searchMarkets(
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
    public ResponseEntity<Market> createMarket(@RequestBody Market market) {
        Market savedMarket = marketService.saveMarket(market);
        return ResponseEntity.ok(savedMarket);
    }
    
    @PutMapping("/{marketId}")
    public ResponseEntity<Market> updateMarket(@PathVariable Long marketId, @RequestBody Market market) {
        Optional<Market> existingMarket = marketService.getMarketById(marketId);
        if (existingMarket.isPresent()) {
            market.setMarketId(marketId);
            Market updatedMarket = marketService.saveMarket(market);
            return ResponseEntity.ok(updatedMarket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{marketId}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long marketId) {
        Optional<Market> market = marketService.getMarketById(marketId);
        if (market.isPresent()) {
            marketService.deleteMarket(marketId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
