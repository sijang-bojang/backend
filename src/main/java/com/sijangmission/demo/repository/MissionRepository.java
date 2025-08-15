package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.core.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    
    List<Mission> findByMissionType(String missionType);
    
    List<Mission> findByTitleContaining(String title);
    
    List<Mission> findByRewardPointsGreaterThan(Integer rewardPoints);
}
