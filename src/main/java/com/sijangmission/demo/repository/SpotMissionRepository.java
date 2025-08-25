package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.relation.SpotMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotMissionRepository extends JpaRepository<SpotMission, Long> {
    
    // 스팟 ID로 연결된 미션들 조회
    List<SpotMission> findBySpotSpotId(Long spotId);
    
    // 미션 ID로 연결된 스팟들 조회
    List<SpotMission> findByMissionMissionId(Long missionId);
    
    // 스팟과 미션 ID로 특정 연결 조회
    Optional<SpotMission> findBySpotSpotIdAndMissionMissionId(Long spotId, Long missionId);
    
    // VISIT 타입 미션만 조회
    @Query("SELECT sm FROM SpotMission sm WHERE sm.spot.spotId = :spotId AND sm.mission.missionType = 'VISIT'")
    List<SpotMission> findVisitMissionsBySpotId(@Param("spotId") Long spotId);
    
    // 스팟 ID로 연결된 VISIT 타입 미션 개수 조회
    @Query("SELECT COUNT(sm) FROM SpotMission sm WHERE sm.spot.spotId = :spotId AND sm.mission.missionType = 'VISIT'")
    long countVisitMissionsBySpotId(@Param("spotId") Long spotId);
    
    // 스팟 ID로 연결된 모든 미션 개수 조회
    @Query("SELECT COUNT(sm) FROM SpotMission sm WHERE sm.spot.spotId = :spotId")
    long countMissionsBySpotId(@Param("spotId") Long spotId);
}
