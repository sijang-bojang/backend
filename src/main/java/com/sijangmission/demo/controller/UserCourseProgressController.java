package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.UserCourseProgress;
import com.sijangmission.demo.service.UserCourseProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-course-progress")
@RequiredArgsConstructor
public class UserCourseProgressController {
    
    private final UserCourseProgressService userCourseProgressService;
    
    @GetMapping
    public ResponseEntity<List<UserCourseProgress>> getAllUserCourseProgresses() {
        List<UserCourseProgress> userCourseProgresses = userCourseProgressService.getAllUserCourseProgresses();
        return ResponseEntity.ok(userCourseProgresses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserCourseProgress> getUserCourseProgressById(@PathVariable Long id) {
        Optional<UserCourseProgress> userCourseProgress = userCourseProgressService.getUserCourseProgressById(id);
        return userCourseProgress.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserCourseProgress>> getUserCourseProgressesByUserId(@PathVariable Long userId) {
        List<UserCourseProgress> userCourseProgresses = userCourseProgressService.getUserCourseProgressesByUserId(userId);
        return ResponseEntity.ok(userCourseProgresses);
    }
    
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<UserCourseProgress>> getUserCourseProgressesByUserIdAndStatus(
            @PathVariable Long userId, @PathVariable String status) {
        List<UserCourseProgress> userCourseProgresses = userCourseProgressService.getUserCourseProgressesByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(userCourseProgresses);
    }
    
    @GetMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<UserCourseProgress> getUserCourseProgressByUserIdAndCourseId(
            @PathVariable Long userId, @PathVariable Long courseId) {
        Optional<UserCourseProgress> userCourseProgress = userCourseProgressService.getUserCourseProgressByUserIdAndCourseId(userId, courseId);
        return userCourseProgress.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<UserCourseProgress> createUserCourseProgress(@RequestBody UserCourseProgress userCourseProgress) {
        UserCourseProgress savedUserCourseProgress = userCourseProgressService.saveUserCourseProgress(userCourseProgress);
        return ResponseEntity.ok(savedUserCourseProgress);
    }
    
    @PostMapping("/start")
    public ResponseEntity<UserCourseProgress> startCourse(
            @RequestParam Long userId, @RequestParam Long courseId) {
        try {
            UserCourseProgress userCourseProgress = userCourseProgressService.startCourse(userId, courseId);
            return ResponseEntity.ok(userCourseProgress);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/progress")
    public ResponseEntity<UserCourseProgress> updateCourseProgress(
            @RequestParam Long userId, @RequestParam Long courseId, @RequestParam Integer currentStep) {
        try {
            UserCourseProgress userCourseProgress = userCourseProgressService.updateCourseProgress(userId, courseId, currentStep);
            return ResponseEntity.ok(userCourseProgress);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/complete")
    public ResponseEntity<UserCourseProgress> completeCourse(
            @RequestParam Long userId, @RequestParam Long courseId) {
        try {
            UserCourseProgress userCourseProgress = userCourseProgressService.completeCourse(userId, courseId);
            return ResponseEntity.ok(userCourseProgress);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserCourseProgress(@PathVariable Long id) {
        Optional<UserCourseProgress> userCourseProgress = userCourseProgressService.getUserCourseProgressById(id);
        if (userCourseProgress.isPresent()) {
            userCourseProgressService.deleteUserCourseProgress(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
