package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {
}
