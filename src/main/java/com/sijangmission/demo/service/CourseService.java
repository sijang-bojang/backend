package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Course;
import com.sijangmission.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    
    private final CourseRepository courseRepository;
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }
    
    public List<Course> getCoursesByMarketId(Long marketId) {
        return courseRepository.findByMarketMarketId(marketId);
    }
    
    public List<Course> getCoursesByName(String name) {
        return courseRepository.findByNameContaining(name);
    }
    
    public List<Course> getCoursesByDescription(String description) {
        return courseRepository.findByDescriptionContaining(description);
    }
    
    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    @Transactional
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
