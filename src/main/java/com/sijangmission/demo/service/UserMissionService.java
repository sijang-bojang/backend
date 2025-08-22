package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.relation.UserMission;
import com.sijangmission.demo.repository.UserMissionRepository;
import com.sijangmission.demo.repository.UserRepository;
import com.sijangmission.demo.repository.MissionRepository;
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
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
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
            UserMission saved = userMissionRepository.save(userMission);
            // User와 Mission을 함께 조회하여 반환
            return userMissionRepository.findByUserIdAndMissionIdWithUserAndMission(userId, missionId)
                    .orElse(saved);
        } else {
            // Create new user mission
            UserMission userMission = new UserMission();
            // User와 Mission 객체 설정 - 명시적으로 설정
            var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            var mission = missionRepository.findById(missionId).orElseThrow(() -> new RuntimeException("Mission not found with id: " + missionId));
            userMission.setUser(user);
            userMission.setMission(mission);
            userMission.setStatus("IN_PROGRESS");
            userMission.setStartedAt(LocalDateTime.now());
            
            // 디버깅을 위한 로그
            System.out.println("DEBUG: Setting user_id = " + userId + ", mission_id = " + missionId);
            
            UserMission saved = userMissionRepository.save(userMission);
            
            // 저장 후 확인
            System.out.println("DEBUG: Saved userMission with id = " + saved.getUserMissionId());
            
            // User와 Mission을 함께 조회하여 반환
            return userMissionRepository.findByUserIdAndMissionIdWithUserAndMission(userId, missionId)
                    .orElse(saved);
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
