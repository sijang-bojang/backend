package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.SpotMission;
import com.sijangmission.demo.domain.SpotMission.SpotMissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotMissionRepository extends JpaRepository<SpotMission, SpotMissionId> {
}
