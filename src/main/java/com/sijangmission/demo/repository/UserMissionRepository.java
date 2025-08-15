package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    
    List<UserMission> findByUserUserId(Long userId);
    
    List<UserMission> findByUserUserIdAndStatus(Long userId, String status);
    
    Optional<UserMission> findByUserUserIdAndMissionMissionId(Long userId, Long missionId);
    
    List<UserMission> findByMissionMissionId(Long missionId);
}
