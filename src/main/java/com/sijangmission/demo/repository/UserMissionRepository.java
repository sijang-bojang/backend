package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.relation.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    
    List<UserMission> findByUserUserId(Long userId);
    
    List<UserMission> findByUserUserIdAndStatus(Long userId, String status);
    
    Optional<UserMission> findByUserUserIdAndMissionMissionId(Long userId, Long missionId);
    
    List<UserMission> findByMissionMissionId(Long missionId);
    
    @Query("SELECT DISTINCT cs.course.courseId FROM CourseSpot cs " +
           "JOIN SpotMission sm ON cs.spot.spotId = sm.spot.spotId " +
           "WHERE sm.mission.missionId = :missionId")
    List<Long> findRelatedCourseIdsByMissionId(@Param("missionId") Long missionId);
    
    @Query("SELECT um FROM UserMission um " +
           "JOIN SpotMission sm ON um.mission.missionId = sm.mission.missionId " +
           "JOIN CourseSpot cs ON sm.spot.spotId = cs.spot.spotId " +
           "WHERE um.user.userId = :userId AND cs.course.courseId = :courseId")
    List<UserMission> findUserMissionsByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);
    
    // User와 Mission을 함께 조회하는 메서드
    @Query("SELECT um FROM UserMission um " +
           "JOIN FETCH um.user " +
           "JOIN FETCH um.mission " +
           "WHERE um.userMissionId = :userMissionId")
    Optional<UserMission> findByIdWithUserAndMission(@Param("userMissionId") Long userMissionId);
    
    @Query("SELECT um FROM UserMission um " +
           "JOIN FETCH um.user " +
           "JOIN FETCH um.mission " +
           "WHERE um.user.userId = :userId AND um.mission.missionId = :missionId")
    Optional<UserMission> findByUserIdAndMissionIdWithUserAndMission(@Param("userId") Long userId, @Param("missionId") Long missionId);
}
