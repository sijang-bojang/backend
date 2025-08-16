package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.Market;
import com.sijangmission.demo.repository.MarketRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/markets")
@RequiredArgsConstructor
public class MarketController {

    private final MarketRepository marketRepository;

    @Operation(summary = "모든 시장 조회")
    @GetMapping
    public List<Market> getMarkets() {
        return marketRepository.findAll();
    }

    @Operation(summary = "시장 등록")
    @PostMapping
    public Market createMarket(@RequestBody Market market) {
        return marketRepository.save(market);
    }
}
