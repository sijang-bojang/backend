package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.UserCourseProgress;
import com.sijangmission.demo.repository.UserCourseProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCourseProgressService {
    
    private final UserCourseProgressRepository userCourseProgressRepository;
    
    public List<UserCourseProgress> getAllUserCourseProgresses() {
        return userCourseProgressRepository.findAll();
    }
    
    public Optional<UserCourseProgress> getUserCourseProgressById(Long id) {
        return userCourseProgressRepository.findById(id);
    }
    
    public List<UserCourseProgress> getUserCourseProgressesByUserId(Long userId) {
        return userCourseProgressRepository.findByUserUserId(userId);
    }
    
    public List<UserCourseProgress> getUserCourseProgressesByUserIdAndStatus(Long userId, String status) {
        return userCourseProgressRepository.findByUserUserIdAndStatus(userId, status);
    }
    
    public Optional<UserCourseProgress> getUserCourseProgressByUserIdAndCourseId(Long userId, Long courseId) {
        return userCourseProgressRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
    }
    
    public List<UserCourseProgress> getUserCourseProgressesByCourseId(Long courseId) {
        return userCourseProgressRepository.findByCourseCourseId(courseId);
    }
    
    @Transactional
    public UserCourseProgress saveUserCourseProgress(UserCourseProgress userCourseProgress) {
        return userCourseProgressRepository.save(userCourseProgress);
    }
    
    @Transactional
    public UserCourseProgress startCourse(Long userId, Long courseId) {
        Optional<UserCourseProgress> existingProgress = userCourseProgressRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
        if (existingProgress.isPresent()) {
            UserCourseProgress progress = existingProgress.get();
            progress.setStatus("IN_PROGRESS");
            progress.setStartedAt(LocalDateTime.now());
            progress.setCurrentStep(1);
            return userCourseProgressRepository.save(progress);
        } else {
            // Create new course progress
            UserCourseProgress progress = new UserCourseProgress();
            progress.setStatus("IN_PROGRESS");
            progress.setStartedAt(LocalDateTime.now());
            progress.setCurrentStep(1);
            return userCourseProgressRepository.save(progress);
        }
    }
    
    @Transactional
    public UserCourseProgress updateCourseProgress(Long userId, Long courseId, Integer currentStep) {
        Optional<UserCourseProgress> progressOpt = userCourseProgressRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
        if (progressOpt.isPresent()) {
            UserCourseProgress progress = progressOpt.get();
            progress.setCurrentStep(currentStep);
            return userCourseProgressRepository.save(progress);
        }
        throw new RuntimeException("User course progress not found");
    }
    
    @Transactional
    public UserCourseProgress completeCourse(Long userId, Long courseId) {
        Optional<UserCourseProgress> progressOpt = userCourseProgressRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
        if (progressOpt.isPresent()) {
            UserCourseProgress progress = progressOpt.get();
            progress.setStatus("COMPLETED");
            progress.setCompletedAt(LocalDateTime.now());
            return userCourseProgressRepository.save(progress);
        }
        throw new RuntimeException("User course progress not found");
    }
    
    @Transactional
    public void deleteUserCourseProgress(Long id) {
        userCourseProgressRepository.deleteById(id);
    }
}
