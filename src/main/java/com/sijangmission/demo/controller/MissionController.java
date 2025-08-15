package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.Mission;
import com.sijangmission.demo.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {
    
    private final MissionService missionService;
    
    @GetMapping
    public ResponseEntity<List<Mission>> getAllMissions() {
        List<Mission> missions = missionService.getAllMissions();
        return ResponseEntity.ok(missions);
    }
    
    @GetMapping("/{missionId}")
    public ResponseEntity<Mission> getMissionById(@PathVariable Long missionId) {
        Optional<Mission> mission = missionService.getMissionById(missionId);
        return mission.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/type/{missionType}")
    public ResponseEntity<List<Mission>> getMissionsByType(@PathVariable String missionType) {
        List<Mission> missions = missionService.getMissionsByType(missionType);
        return ResponseEntity.ok(missions);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Mission>> searchMissions(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer minRewardPoints) {
        
        if (title != null && !title.isEmpty()) {
            return ResponseEntity.ok(missionService.getMissionsByTitle(title));
        } else if (minRewardPoints != null) {
            return ResponseEntity.ok(missionService.getMissionsByMinRewardPoints(minRewardPoints));
        } else {
            return ResponseEntity.ok(missionService.getAllMissions());
        }
    }
    
    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody Mission mission) {
        Mission savedMission = missionService.saveMission(mission);
        return ResponseEntity.ok(savedMission);
    }
    
    @PutMapping("/{missionId}")
    public ResponseEntity<Mission> updateMission(@PathVariable Long missionId, @RequestBody Mission mission) {
        Optional<Mission> existingMission = missionService.getMissionById(missionId);
        if (existingMission.isPresent()) {
            mission.setMissionId(missionId);
            Mission updatedMission = missionService.saveMission(mission);
            return ResponseEntity.ok(updatedMission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{missionId}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long missionId) {
        Optional<Mission> mission = missionService.getMissionById(missionId);
        if (mission.isPresent()) {
            missionService.deleteMission(missionId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
