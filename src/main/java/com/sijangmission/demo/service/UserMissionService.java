package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.relation.UserMission;
import com.sijangmission.demo.repository.UserMissionRepository;
import com.sijangmission.demo.service.UserCourseProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionService {
    
    private final UserMissionRepository userMissionRepository;
    private final UserCourseProgressService userCourseProgressService;
    
    public List<UserMission> getAllUserMissions() {
        return userMissionRepository.findAll();
    }
    
    public Optional<UserMission> getUserMissionById(Long userMissionId) {
        return userMissionRepository.findById(userMissionId);
    }
    
    public List<UserMission> getUserMissionsByUserId(Long userId) {
        return userMissionRepository.findByUserUserId(userId);
    }
    
    public List<UserMission> getUserMissionsByUserIdAndStatus(Long userId, String status) {
        return userMissionRepository.findByUserUserIdAndStatus(userId, status);
    }
    
    public Optional<UserMission> getUserMissionByUserIdAndMissionId(Long userId, Long missionId) {
        return userMissionRepository.findByUserUserIdAndMissionMissionId(userId, missionId);
    }
    
    public List<UserMission> getUserMissionsByMissionId(Long missionId) {
        return userMissionRepository.findByMissionMissionId(missionId);
    }
    
    @Transactional
    public UserMission saveUserMission(UserMission userMission) {
        return userMissionRepository.save(userMission);
    }
    
    @Transactional
    public UserMission startMission(Long userId, Long missionId) {
        Optional<UserMission> existingUserMission = userMissionRepository.findByUserUserIdAndMissionMissionId(userId, missionId);
        if (existingUserMission.isPresent()) {
            UserMission userMission = existingUserMission.get();
            userMission.setStatus("IN_PROGRESS");
            userMission.setStartedAt(LocalDateTime.now());
            return userMissionRepository.save(userMission);
        } else {
            // Create new user mission
            UserMission userMission = new UserMission();
            userMission.setStatus("IN_PROGRESS");
            userMission.setStartedAt(LocalDateTime.now());
            return userMissionRepository.save(userMission);
        }
    }
    
    	@Transactional
	public UserMission completeMission(Long userId, Long missionId) {
		Optional<UserMission> userMissionOpt = userMissionRepository.findByUserUserIdAndMissionMissionId(userId, missionId);
		if (userMissionOpt.isPresent()) {
			UserMission userMission = userMissionOpt.get();
			userMission.setStatus("COMPLETED");
			userMission.setCompletedAt(LocalDateTime.now());
			UserMission savedUserMission = userMissionRepository.save(userMission);
			
			// 미션 완료 후 해당 미션이 속한 코스들의 완료 상태 체크
			checkAndCompleteRelatedCourses(userId, missionId);
			
			return savedUserMission;
		}
		throw new RuntimeException("User mission not found");
	}
    
    @Transactional
    public void deleteUserMission(Long userMissionId) {
        userMissionRepository.deleteById(userMissionId);
    }
    
    /**
     * 미션 완료 후 해당 미션이 속한 코스들의 완료 상태를 체크
     */
    private void checkAndCompleteRelatedCourses(Long userId, Long missionId) {
        // 해당 미션이 속한 모든 코스 조회
        List<Long> relatedCourseIds = userMissionRepository.findRelatedCourseIdsByMissionId(missionId);
        
        // 각 코스에 대해 모든 미션이 완료되었는지 체크
        for (Long courseId : relatedCourseIds) {
            userCourseProgressService.checkAndCompleteCourseIfAllMissionsDone(userId, courseId);
        }
    }
}
