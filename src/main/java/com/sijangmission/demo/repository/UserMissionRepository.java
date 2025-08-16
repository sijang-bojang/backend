package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}
