package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.core.Course;
import com.sijangmission.demo.dto.CourseDto;
import com.sijangmission.demo.dto.CourseRecommendationRequest;
import com.sijangmission.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    
    private final CourseService courseService;
    
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long courseId) {
        Optional<CourseDto> course = courseService.getCourseById(courseId);
        return course.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/market/{marketId}")
    public ResponseEntity<List<CourseDto>> getCoursesByMarketId(@PathVariable Long marketId) {
        List<CourseDto> courses = courseService.getCoursesByMarketId(marketId);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> searchCourses(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        
        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(courseService.getCoursesByName(name));
        } else if (description != null && !description.isEmpty()) {
            return ResponseEntity.ok(courseService.getCoursesByDescription(description));
        } else {
            return ResponseEntity.ok(courseService.getAllCourses());
        }
    }
    
    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody Course course) {
        CourseDto savedCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(savedCourse);
    }
    
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        Optional<CourseDto> existingCourse = courseService.getCourseById(courseId);
        if (existingCourse.isPresent()) {
            course.setCourseId(courseId);
            CourseDto updatedCourse = courseService.saveCourse(course);
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        Optional<CourseDto> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/recommend")
    public ResponseEntity<CourseDto> recommendCourse(@RequestBody CourseRecommendationRequest request) {
        try {
            CourseDto recommendedCourse = courseService.recommendCourse(request);
            return ResponseEntity.ok(recommendedCourse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
