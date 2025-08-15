package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.Mission;
import com.sijangmission.demo.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    
    private final MissionRepository missionRepository;
    
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }
    
    public Optional<Mission> getMissionById(Long missionId) {
        return missionRepository.findById(missionId);
    }
    
    public List<Mission> getMissionsByType(String missionType) {
        return missionRepository.findByMissionType(missionType);
    }
    
    public List<Mission> getMissionsByTitle(String title) {
        return missionRepository.findByTitleContaining(title);
    }
    
    public List<Mission> getMissionsByMinRewardPoints(Integer minRewardPoints) {
        return missionRepository.findByRewardPointsGreaterThan(minRewardPoints);
    }
    
    @Transactional
    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }
    
    @Transactional
    public void deleteMission(Long missionId) {
        missionRepository.deleteById(missionId);
    }
}
