package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.core.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    
    List<Market> findByNameContaining(String name);
    
    List<Market> findByAddressContaining(String address);
}
