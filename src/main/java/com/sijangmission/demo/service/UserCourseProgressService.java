package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.relation.UserCourseProgress;
import com.sijangmission.demo.domain.relation.UserMission;
import com.sijangmission.demo.dto.UserCourseProgressDto;
import com.sijangmission.demo.mapper.UserCourseProgressMapper;
import com.sijangmission.demo.repository.UserCourseProgressRepository;
import com.sijangmission.demo.repository.UserMissionRepository;
import com.sijangmission.demo.repository.UserRepository;
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
	private final UserCourseProgressMapper userCourseProgressMapper;
	private final UserMissionRepository userMissionRepository;
	private final UserRepository userRepository;
    
    	public List<UserCourseProgressDto> getAllUserCourseProgresses() {
		List<UserCourseProgress> entities = userCourseProgressRepository.findAll();
		return userCourseProgressMapper.toDtoList(entities);
	}
	
	public Optional<UserCourseProgressDto> getUserCourseProgressById(Long id) {
		Optional<UserCourseProgress> entity = userCourseProgressRepository.findById(id);
		return entity.map(userCourseProgressMapper::toDto);
	}
	
	public List<UserCourseProgressDto> getUserCourseProgressesByUserId(Long userId) {
		List<UserCourseProgress> entities = userCourseProgressRepository.findByUserUserId(userId);
		return userCourseProgressMapper.toDtoList(entities);
	}
	
	public List<UserCourseProgressDto> getUserCourseProgressesByUserIdAndStatus(Long userId, String status) {
		List<UserCourseProgress> entities = userCourseProgressRepository.findByUserUserIdAndStatus(userId, status);
		return userCourseProgressMapper.toDtoList(entities);
	}
	
	public Optional<UserCourseProgressDto> getUserCourseProgressByUserIdAndCourseId(Long userId, Long courseId) {
		Optional<UserCourseProgress> entity = userCourseProgressRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
		return entity.map(userCourseProgressMapper::toDto);
	}
	
	public List<UserCourseProgressDto> getUserCourseProgressesByCourseId(Long courseId) {
		List<UserCourseProgress> entities = userCourseProgressRepository.findByCourseCourseId(courseId);
		return userCourseProgressMapper.toDtoList(entities);
	}
    
    	@Transactional
	public UserCourseProgressDto saveUserCourseProgress(UserCourseProgress userCourseProgress) {
		UserCourseProgress savedEntity = userCourseProgressRepository.save(userCourseProgress);
		return userCourseProgressMapper.toDto(savedEntity);
	}
	
	@Transactional
	public UserCourseProgressDto startCourse(Long userId, Long courseId) {
		Optional<UserCourseProgress> existingProgress = userCourseProgressRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
		if (existingProgress.isPresent()) {
			UserCourseProgress progress = existingProgress.get();
			progress.setStatus("IN_PROGRESS");
			progress.setStartedAt(LocalDateTime.now());
			progress.setCurrentStep(1);
			UserCourseProgress savedEntity = userCourseProgressRepository.save(progress);
			return userCourseProgressMapper.toDto(savedEntity);
		} else {
			// Create new course progress
			UserCourseProgress progress = new UserCourseProgress();
			progress.setStatus("IN_PROGRESS");
			progress.setStartedAt(LocalDateTime.now());
			progress.setCurrentStep(1);
			UserCourseProgress savedEntity = userCourseProgressRepository.save(progress);
			return userCourseProgressMapper.toDto(savedEntity);
		}
	}
	
	@Transactional
	public UserCourseProgressDto updateCourseProgress(Long userId, Long courseId, Integer currentStep) {
		Optional<UserCourseProgress> progressOpt = userCourseProgressRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
		if (progressOpt.isPresent()) {
			UserCourseProgress progress = progressOpt.get();
			progress.setCurrentStep(currentStep);
			UserCourseProgress savedEntity = userCourseProgressRepository.save(progress);
			return userCourseProgressMapper.toDto(savedEntity);
		}
		throw new RuntimeException("User course progress not found");
	}
	
	@Transactional
	public UserCourseProgressDto completeCourse(Long userId, Long courseId) {
		Optional<UserCourseProgress> progressOpt = userCourseProgressRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
		if (progressOpt.isPresent()) {
			UserCourseProgress progress = progressOpt.get();
			
			// 이미 완료된 코스인지 확인
			if ("COMPLETED".equals(progress.getStatus())) {
				return userCourseProgressMapper.toDto(progress);
			}
			
			progress.setStatus("COMPLETED");
			progress.setCompletedAt(LocalDateTime.now());
			UserCourseProgress savedEntity = userCourseProgressRepository.save(progress);
			
			// 코스 완료 시 스탬프 추가 (중복 방지)
			userRepository.findById(userId).ifPresent(user -> {
				user.addCompleteStamp();
				userRepository.save(user);
			});
			
			return userCourseProgressMapper.toDto(savedEntity);
		}
		throw new RuntimeException("User course progress not found");
	}
    
    @Transactional
    public void deleteUserCourseProgress(Long id) {
        userCourseProgressRepository.deleteById(id);
    }
    
    /**
     * 코스의 모든 미션이 완료되었는지 확인하고, 완료되면 코스를 자동으로 완료
     */
    @Transactional
    public void checkAndCompleteCourseIfAllMissionsDone(Long userId, Long courseId) {
        // 코스에 포함된 모든 미션 조회 (간단한 방법으로 수정)
        List<UserMission> courseMissions = userMissionRepository.findUserMissionsByUserIdAndCourseId(userId, courseId);
        
        // 모든 미션이 완료되었는지 확인
        boolean allMissionsCompleted = courseMissions.stream()
                .allMatch(userMission -> "COMPLETED".equals(userMission.getStatus()));
        
        if (allMissionsCompleted && !courseMissions.isEmpty()) {
            // 모든 미션이 완료되면 코스도 완료
            completeCourse(userId, courseId);
        }
    }
}
