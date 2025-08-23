package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Mission;
import com.sijangmission.demo.domain.core.Spot;
import com.sijangmission.demo.domain.relation.SpotMission;
import com.sijangmission.demo.dto.SpotMissionDto;
import com.sijangmission.demo.mapper.SpotMissionMapper;
import com.sijangmission.demo.repository.MissionRepository;
import com.sijangmission.demo.repository.SpotMissionRepository;
import com.sijangmission.demo.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpotMissionService {
    
    private final SpotMissionRepository spotMissionRepository;
    private final SpotRepository spotRepository;
    private final MissionRepository missionRepository;
    private final SpotMissionMapper spotMissionMapper;
    
    // 스팟에 연결된 모든 미션 조회
    public List<SpotMissionDto> getMissionsBySpotId(Long spotId) {
        List<SpotMission> spotMissions = spotMissionRepository.findBySpotSpotId(spotId);
        return spotMissionMapper.toDtoList(spotMissions);
    }
    
    // 스팟에 연결된 VISIT 타입 미션만 조회
    public List<SpotMissionDto> getVisitMissionsBySpotId(Long spotId) {
        List<SpotMission> visitMissions = spotMissionRepository.findVisitMissionsBySpotId(spotId);
        return spotMissionMapper.toDtoList(visitMissions);
    }
    
    // 미션에 연결된 모든 스팟 조회
    public List<SpotMissionDto> getSpotsByMissionId(Long missionId) {
        List<SpotMission> spotMissions = spotMissionRepository.findByMissionMissionId(missionId);
        return spotMissionMapper.toDtoList(spotMissions);
    }
    
    // 특정 스팟-미션 연결 조회
    public Optional<SpotMissionDto> getSpotMissionBySpotIdAndMissionId(Long spotId, Long missionId) {
        Optional<SpotMission> spotMission = spotMissionRepository.findBySpotSpotIdAndMissionMissionId(spotId, missionId);
        return spotMission.map(spotMissionMapper::toDto);
    }
    
    // 스팟에 미션 연결
    @Transactional
    public SpotMissionDto connectSpotToMission(Long spotId, Long missionId) {
        // 이미 연결되어 있는지 확인
        Optional<SpotMission> existingConnection = spotMissionRepository.findBySpotSpotIdAndMissionMissionId(spotId, missionId);
        if (existingConnection.isPresent()) {
            return spotMissionMapper.toDto(existingConnection.get());
        }
        
        // Spot과 Mission 조회
        Optional<Spot> spotOpt = spotRepository.findById(spotId);
        Optional<Mission> missionOpt = missionRepository.findById(missionId);
        
        if (spotOpt.isEmpty() || missionOpt.isEmpty()) {
            throw new RuntimeException("Spot or Mission not found");
        }
        
        // 새로운 연결 생성
        SpotMission spotMission = new SpotMission();
        spotMission.setSpot(spotOpt.get());
        spotMission.setMission(missionOpt.get());
        
        SpotMission savedSpotMission = spotMissionRepository.save(spotMission);
        return spotMissionMapper.toDto(savedSpotMission);
    }
    
    // 스팟에 VISIT 타입 미션 연결
    @Transactional
    public SpotMissionDto connectSpotToVisitMission(Long spotId, Long missionId) {
        // Mission이 VISIT 타입인지 확인
        Optional<Mission> missionOpt = missionRepository.findById(missionId);
        if (missionOpt.isEmpty()) {
            throw new RuntimeException("Mission not found");
        }
        
        Mission mission = missionOpt.get();
        if (!"VISIT".equals(mission.getMissionType())) {
            throw new RuntimeException("Mission is not VISIT type");
        }
        
        return connectSpotToMission(spotId, missionId);
    }
    
    // 스팟-미션 연결 해제
    @Transactional
    public void disconnectSpotFromMission(Long spotId, Long missionId) {
        Optional<SpotMission> spotMission = spotMissionRepository.findBySpotSpotIdAndMissionMissionId(spotId, missionId);
        spotMission.ifPresent(spotMissionRepository::delete);
    }
    
    // 스팟에 연결된 미션 개수 조회
    public long getMissionCountBySpotId(Long spotId) {
        return spotMissionRepository.countMissionsBySpotId(spotId);
    }
    
    // 스팟에 연결된 VISIT 타입 미션 개수 조회
    public long getVisitMissionCountBySpotId(Long spotId) {
        return spotMissionRepository.countVisitMissionsBySpotId(spotId);
    }
    
    // 모든 스팟-미션 연결 조회
    public List<SpotMissionDto> getAllSpotMissions() {
        List<SpotMission> allSpotMissions = spotMissionRepository.findAll();
        return spotMissionMapper.toDtoList(allSpotMissions);
    }
}
