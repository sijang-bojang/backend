package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
}
