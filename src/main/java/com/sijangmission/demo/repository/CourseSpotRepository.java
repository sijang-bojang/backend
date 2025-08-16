package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.CourseSpot;
import com.sijangmission.demo.domain.CourseSpot.CourseSpotId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpotRepository extends JpaRepository<CourseSpot, CourseSpotId> {
}
