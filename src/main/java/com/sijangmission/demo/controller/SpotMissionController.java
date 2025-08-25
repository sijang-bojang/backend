package com.sijangmission.demo.controller;

import com.sijangmission.demo.dto.SpotMissionDto;
import com.sijangmission.demo.dto.MissionDto;
import com.sijangmission.demo.service.SpotMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotMissionController {
    
    private final SpotMissionService spotMissionService;
    
    // 스팟에 연결된 모든 미션 조회
    @GetMapping("/{spotId}/missions")
    public ResponseEntity<List<MissionDto>> getMissionsBySpotId(@PathVariable Long spotId) {
        List<MissionDto> missions = spotMissionService.getMissionsBySpotId(spotId);
        return ResponseEntity.ok(missions);
    }
    
    // 스팟에 연결된 VISIT 타입 미션만 조회
    @GetMapping("/{spotId}/missions/visit")
    public ResponseEntity<List<MissionDto>> getVisitMissionsBySpotId(@PathVariable Long spotId) {
        List<MissionDto> visitMissions = spotMissionService.getVisitMissionsBySpotId(spotId);
        return ResponseEntity.ok(visitMissions);
    }
    
    // 특정 스팟의 특정 미션 연결 조회
    @GetMapping("/{spotId}/missions/{missionId}")
    public ResponseEntity<SpotMissionDto> getSpotMissionBySpotIdAndMissionId(
            @PathVariable Long spotId, @PathVariable Long missionId) {
        Optional<SpotMissionDto> spotMission = spotMissionService.getSpotMissionBySpotIdAndMissionId(spotId, missionId);
        return spotMission.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // 스팟에 미션 연결
    @PostMapping("/{spotId}/missions/{missionId}")
    public ResponseEntity<SpotMissionDto> connectSpotToMission(
            @PathVariable Long spotId, @PathVariable Long missionId) {
        try {
            SpotMissionDto spotMission = spotMissionService.connectSpotToMission(spotId, missionId);
            return ResponseEntity.ok(spotMission);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // 스팟에 VISIT 타입 미션 연결
    @PostMapping("/{spotId}/missions/{missionId}/visit")
    public ResponseEntity<SpotMissionDto> connectSpotToVisitMission(
            @PathVariable Long spotId, @PathVariable Long missionId) {
        try {
            SpotMissionDto spotMission = spotMissionService.connectSpotToVisitMission(spotId, missionId);
            return ResponseEntity.ok(spotMission);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // 스팟-미션 연결 해제
    @DeleteMapping("/{spotId}/missions/{missionId}")
    public ResponseEntity<Void> disconnectSpotFromMission(
            @PathVariable Long spotId, @PathVariable Long missionId) {
        try {
            spotMissionService.disconnectSpotFromMission(spotId, missionId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // 스팟에 연결된 미션 개수 조회
    @GetMapping("/{spotId}/missions/count")
    public ResponseEntity<Long> getMissionCountBySpotId(@PathVariable Long spotId) {
        long count = spotMissionService.getMissionCountBySpotId(spotId);
        return ResponseEntity.ok(count);
    }
    
    // 스팟에 연결된 VISIT 타입 미션 개수 조회
    @GetMapping("/{spotId}/missions/visit/count")
    public ResponseEntity<Long> getVisitMissionCountBySpotId(@PathVariable Long spotId) {
        long count = spotMissionService.getVisitMissionCountBySpotId(spotId);
        return ResponseEntity.ok(count);
    }
}
