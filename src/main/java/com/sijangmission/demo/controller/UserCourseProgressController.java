package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.relation.UserCourseProgress;
import com.sijangmission.demo.dto.UserCourseProgressDto;
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
	public ResponseEntity<List<UserCourseProgressDto>> getAllUserCourseProgresses() {
		List<UserCourseProgressDto> userCourseProgresses = userCourseProgressService.getAllUserCourseProgresses();
		return ResponseEntity.ok(userCourseProgresses);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserCourseProgressDto> getUserCourseProgressById(@PathVariable Long id) {
		Optional<UserCourseProgressDto> userCourseProgress = userCourseProgressService.getUserCourseProgressById(id);
		return userCourseProgress.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<UserCourseProgressDto>> getUserCourseProgressesByUserId(@PathVariable Long userId) {
		List<UserCourseProgressDto> userCourseProgresses = userCourseProgressService.getUserCourseProgressesByUserId(userId);
		return ResponseEntity.ok(userCourseProgresses);
	}
	
	@GetMapping("/user/{userId}/status/{status}")
	public ResponseEntity<List<UserCourseProgressDto>> getUserCourseProgressesByUserIdAndStatus(
			@PathVariable Long userId, @PathVariable String status) {
		List<UserCourseProgressDto> userCourseProgresses = userCourseProgressService.getUserCourseProgressesByUserIdAndStatus(userId, status);
		return ResponseEntity.ok(userCourseProgresses);
	}
	
	@GetMapping("/user/{userId}/course/{courseId}")
	public ResponseEntity<UserCourseProgressDto> getUserCourseProgressByUserIdAndCourseId(
			@PathVariable Long userId, @PathVariable Long courseId) {
		Optional<UserCourseProgressDto> userCourseProgress = userCourseProgressService.getUserCourseProgressByUserIdAndCourseId(userId, courseId);
		return userCourseProgress.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
    
    	@PostMapping
	public ResponseEntity<UserCourseProgressDto> createUserCourseProgress(@RequestBody UserCourseProgress userCourseProgress) {
		UserCourseProgressDto savedUserCourseProgress = userCourseProgressService.saveUserCourseProgress(userCourseProgress);
		return ResponseEntity.ok(savedUserCourseProgress);
	}
	
	@PostMapping("/start")
	public ResponseEntity<UserCourseProgressDto> startCourse(
			@RequestParam Long userId, @RequestParam Long courseId) {
		try {
			UserCourseProgressDto userCourseProgress = userCourseProgressService.startCourse(userId, courseId);
			return ResponseEntity.ok(userCourseProgress);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/progress")
	public ResponseEntity<UserCourseProgressDto> updateCourseProgress(
			@RequestParam Long userId, @RequestParam Long courseId, @RequestParam Integer currentStep) {
		try {
			UserCourseProgressDto userCourseProgress = userCourseProgressService.updateCourseProgress(userId, courseId, currentStep);
			return ResponseEntity.ok(userCourseProgress);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/complete")
	public ResponseEntity<UserCourseProgressDto> completeCourse(
			@RequestParam Long userId, @RequestParam Long courseId) {
		try {
			UserCourseProgressDto userCourseProgress = userCourseProgressService.completeCourse(userId, courseId);
			return ResponseEntity.ok(userCourseProgress);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
    
    	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserCourseProgress(@PathVariable Long id) {
		Optional<UserCourseProgressDto> userCourseProgress = userCourseProgressService.getUserCourseProgressById(id);
		if (userCourseProgress.isPresent()) {
			userCourseProgressService.deleteUserCourseProgress(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
