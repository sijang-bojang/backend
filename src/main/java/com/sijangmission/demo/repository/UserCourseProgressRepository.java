package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.UserCourseProgress;
import com.sijangmission.demo.domain.UserCourseProgress.UserCourseProgressId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseProgressRepository extends JpaRepository<UserCourseProgress, UserCourseProgressId> {
}
