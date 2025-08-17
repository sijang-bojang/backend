package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Market;
import com.sijangmission.demo.dto.MarketDto;
import com.sijangmission.demo.mapper.MarketMapper;
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
    private final MarketMapper marketMapper;
    
    public List<MarketDto> getAllMarkets() {
        List<Market> markets = marketRepository.findAll();
        return markets.stream()
                .map(marketMapper::toDto)
                .toList();
    }
    
    public Optional<MarketDto> getMarketById(Long marketId) {
        Optional<Market> market = marketRepository.findById(marketId);
        return market.map(marketMapper::toDto);
    }
    
    public List<MarketDto> getMarketsByName(String name) {
        List<Market> markets = marketRepository.findByNameContaining(name);
        return markets.stream()
                .map(marketMapper::toDto)
                .toList();
    }
    
    public List<MarketDto> getMarketsByAddress(String address) {
        List<Market> markets = marketRepository.findByAddressContaining(address);
        return markets.stream()
                .map(marketMapper::toDto)
                .toList();
    }
    
    @Transactional
    public MarketDto saveMarket(Market market) {
        Market savedMarket = marketRepository.save(market);
        return marketMapper.toDto(savedMarket);
    }
    
    @Transactional
    public void deleteMarket(Long marketId) {
        marketRepository.deleteById(marketId);
    }
}
