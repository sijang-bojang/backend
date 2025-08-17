package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Course;
import com.sijangmission.demo.dto.CourseDto;
import com.sijangmission.demo.mapper.CourseMapper;
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
    private final CourseMapper courseMapper;
    
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDtoList(courses);
    }
    
    public Optional<CourseDto> getCourseById(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.map(courseMapper::toDto);
    }
    
    public List<CourseDto> getCoursesByMarketId(Long marketId) {
        List<Course> courses = courseRepository.findByMarketMarketId(marketId);
        return courseMapper.toDtoList(courses);
    }
    
    public List<CourseDto> getCoursesByName(String name) {
        List<Course> courses = courseRepository.findByNameContaining(name);
        return courseMapper.toDtoList(courses);
    }
    
    public List<CourseDto> getCoursesByDescription(String description) {
        List<Course> courses = courseRepository.findByDescriptionContaining(description);
        return courseMapper.toDtoList(courses);
    }
    
    @Transactional
    public CourseDto saveCourse(Course course) {
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDto(savedCourse);
    }
    
    @Transactional
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
