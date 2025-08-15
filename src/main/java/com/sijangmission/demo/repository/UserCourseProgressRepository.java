package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.relation.UserCourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCourseProgressRepository extends JpaRepository<UserCourseProgress, Long> {
    
    List<UserCourseProgress> findByUserUserId(Long userId);
    
    List<UserCourseProgress> findByUserUserIdAndStatus(Long userId, String status);
    
    Optional<UserCourseProgress> findByUserUserIdAndCourseCourseId(Long userId, Long courseId);
    
    List<UserCourseProgress> findByCourseCourseId(Long courseId);
}
