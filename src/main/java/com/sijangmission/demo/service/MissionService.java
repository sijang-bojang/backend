package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Mission;
import com.sijangmission.demo.dto.MissionDto;
import com.sijangmission.demo.mapper.MissionMapper;
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
    private final MissionMapper missionMapper;
    
    public List<MissionDto> getAllMissions() {
        List<Mission> missions = missionRepository.findAll();
        return missionMapper.toDtoList(missions);
    }
    
    public Optional<MissionDto> getMissionById(Long missionId) {
        Optional<Mission> mission = missionRepository.findById(missionId);
        return mission.map(missionMapper::toDto);
    }
    
    public List<MissionDto> getMissionsByType(String missionType) {
        List<Mission> missions = missionRepository.findByMissionType(missionType);
        return missionMapper.toDtoList(missions);
    }
    
    public List<MissionDto> getMissionsByTitle(String title) {
        List<Mission> missions = missionRepository.findByTitleContaining(title);
        return missionMapper.toDtoList(missions);
    }
    
    public List<MissionDto> getMissionsByMinRewardPoints(Integer minRewardPoints) {
        List<Mission> missions = missionRepository.findByRewardPointsGreaterThan(minRewardPoints);
        return missionMapper.toDtoList(missions);
    }
    
    @Transactional
    public MissionDto saveMission(Mission mission) {
        Mission savedMission = missionRepository.save(mission);
        return missionMapper.toDto(savedMission);
    }
    
    @Transactional
    public void deleteMission(Long missionId) {
        missionRepository.deleteById(missionId);
    }
}
