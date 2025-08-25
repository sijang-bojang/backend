package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.core.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    List<Course> findByMarketMarketId(Long marketId);
    
    List<Course> findByNameContaining(String name);
    
    List<Course> findByDescriptionContaining(String description);
}
