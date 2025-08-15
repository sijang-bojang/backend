package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.Market;
import com.sijangmission.demo.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketService {
    
    private final MarketRepository marketRepository;
    
    public List<Market> getAllMarkets() {
        return marketRepository.findAll();
    }
    
    public Optional<Market> getMarketById(Long marketId) {
        return marketRepository.findById(marketId);
    }
    
    public List<Market> getMarketsByName(String name) {
        return marketRepository.findByNameContaining(name);
    }
    
    public List<Market> getMarketsByAddress(String address) {
        return marketRepository.findByAddressContaining(address);
    }
    
    @Transactional
    public Market saveMarket(Market market) {
        return marketRepository.save(market);
    }
    
    @Transactional
    public void deleteMarket(Long marketId) {
        marketRepository.deleteById(marketId);
    }
}
