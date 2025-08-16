package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
