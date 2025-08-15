package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.relation.UserMission;
import com.sijangmission.demo.service.UserMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-missions")
@RequiredArgsConstructor
public class UserMissionController {
    
    private final UserMissionService userMissionService;
    
    @GetMapping
    public ResponseEntity<List<UserMission>> getAllUserMissions() {
        List<UserMission> userMissions = userMissionService.getAllUserMissions();
        return ResponseEntity.ok(userMissions);
    }
    
    @GetMapping("/{userMissionId}")
    public ResponseEntity<UserMission> getUserMissionById(@PathVariable Long userMissionId) {
        Optional<UserMission> userMission = userMissionService.getUserMissionById(userMissionId);
        return userMission.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserMission>> getUserMissionsByUserId(@PathVariable Long userId) {
        List<UserMission> userMissions = userMissionService.getUserMissionsByUserId(userId);
        return ResponseEntity.ok(userMissions);
    }
    
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<UserMission>> getUserMissionsByUserIdAndStatus(
            @PathVariable Long userId, @PathVariable String status) {
        List<UserMission> userMissions = userMissionService.getUserMissionsByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(userMissions);
    }
    
    @GetMapping("/user/{userId}/mission/{missionId}")
    public ResponseEntity<UserMission> getUserMissionByUserIdAndMissionId(
            @PathVariable Long userId, @PathVariable Long missionId) {
        Optional<UserMission> userMission = userMissionService.getUserMissionByUserIdAndMissionId(userId, missionId);
        return userMission.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<UserMission> createUserMission(@RequestBody UserMission userMission) {
        UserMission savedUserMission = userMissionService.saveUserMission(userMission);
        return ResponseEntity.ok(savedUserMission);
    }
    
    @PostMapping("/start")
    public ResponseEntity<UserMission> startMission(
            @RequestParam Long userId, @RequestParam Long missionId) {
        try {
            UserMission userMission = userMissionService.startMission(userId, missionId);
            return ResponseEntity.ok(userMission);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/complete")
    public ResponseEntity<UserMission> completeMission(
            @RequestParam Long userId, @RequestParam Long missionId) {
        try {
            UserMission userMission = userMissionService.completeMission(userId, missionId);
            return ResponseEntity.ok(userMission);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{userMissionId}")
    public ResponseEntity<Void> deleteUserMission(@PathVariable Long userMissionId) {
        Optional<UserMission> userMission = userMissionService.getUserMissionById(userMissionId);
        if (userMission.isPresent()) {
            userMissionService.deleteUserMission(userMissionId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
