package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.relation.UserMission;
import com.sijangmission.demo.dto.UserMissionDto;
import com.sijangmission.demo.service.UserMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-missions")
@RequiredArgsConstructor
public class UserMissionController {
    
    private final UserMissionService userMissionService;
    
    @GetMapping
    public ResponseEntity<List<UserMissionDto>> getAllUserMissions() {
        List<UserMission> userMissions = userMissionService.getAllUserMissions();
        List<UserMissionDto> dtos = userMissions.stream()
                .map(UserMissionDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/{userMissionId}")
    public ResponseEntity<UserMissionDto> getUserMissionById(@PathVariable Long userMissionId) {
        Optional<UserMission> userMission = userMissionService.getUserMissionById(userMissionId);
        return userMission.map(um -> ResponseEntity.ok(UserMissionDto.fromEntity(um)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserMissionDto>> getUserMissionsByUserId(@PathVariable Long userId) {
        List<UserMission> userMissions = userMissionService.getUserMissionsByUserId(userId);
        List<UserMissionDto> dtos = userMissions.stream()
                .map(UserMissionDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<UserMissionDto>> getUserMissionsByUserIdAndStatus(
            @PathVariable Long userId, @PathVariable String status) {
        List<UserMission> userMissions = userMissionService.getUserMissionsByUserIdAndStatus(userId, status);
        List<UserMissionDto> dtos = userMissions.stream()
                .map(UserMissionDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/user/{userId}/mission/{missionId}")
    public ResponseEntity<UserMissionDto> getUserMissionByUserIdAndMissionId(
            @PathVariable Long userId, @PathVariable Long missionId) {
        Optional<UserMission> userMission = userMissionService.getUserMissionByUserIdAndMissionId(userId, missionId);
        return userMission.map(um -> ResponseEntity.ok(UserMissionDto.fromEntity(um)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<UserMissionDto> createUserMission(@RequestBody UserMission userMission) {
        UserMission savedUserMission = userMissionService.saveUserMission(userMission);
        return ResponseEntity.ok(UserMissionDto.fromEntity(savedUserMission));
    }
    
    @PostMapping("/start")
    public ResponseEntity<UserMissionDto> startMission(
            @RequestParam Long userId, @RequestParam Long missionId) {
        try {
            UserMission userMission = userMissionService.startMission(userId, missionId);
            return ResponseEntity.ok(UserMissionDto.fromEntity(userMission));
        } catch (RuntimeException e) {
            System.err.println("ERROR in startMission: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/complete")
    public ResponseEntity<UserMissionDto> completeMission(
            @RequestParam Long userId, @RequestParam Long missionId) {
        try {
            UserMission userMission = userMissionService.completeMission(userId, missionId);
            return ResponseEntity.ok(UserMissionDto.fromEntity(userMission));
        } catch (RuntimeException e) {
            System.err.println("ERROR in completeMission: " + e.getMessage());
            e.printStackTrace();
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
