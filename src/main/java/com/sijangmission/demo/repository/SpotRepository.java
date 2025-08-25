package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.core.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
    
    List<Spot> findByMarketMarketId(Long marketId);
    
    List<Spot> findByCategory(String category);
    
    List<Spot> findByNameContaining(String name);
    
    List<Spot> findByCategoryAndMarketMarketId(String category, Long marketId);
}
