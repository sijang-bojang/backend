package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}
