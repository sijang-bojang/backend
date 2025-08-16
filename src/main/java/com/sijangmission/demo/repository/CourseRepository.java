package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
