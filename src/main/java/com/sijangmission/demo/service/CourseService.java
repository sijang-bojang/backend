package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Course;
import com.sijangmission.demo.dto.CourseDto;
import com.sijangmission.demo.dto.CourseRecommendationRequest;
import com.sijangmission.demo.mapper.CourseMapper;
import com.sijangmission.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${spring.ai.openai.api-key}")
	private String openaiApiKey;
    
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
    
    public CourseDto recommendCourse(CourseRecommendationRequest request) {
        // 해당 시장의 모든 코스 조회
        List<Course> courses = courseRepository.findByMarketMarketId(request.getMarketId());
        
        if (courses.isEmpty()) {
            throw new RuntimeException("해당 시장에 코스가 없습니다.");
        }
        
        // 코스 정보를 문자열로 변환
        StringBuilder coursesInfo = new StringBuilder();
        for (Course course : courses) {
            coursesInfo.append("코스 ID: ").append(course.getCourseId())
                    .append(", 이름: ").append(course.getName())
                    .append(", 설명: ").append(course.getDescription())
                    .append("\n");
        }
        
        // AI 프롬프트 생성
        String prompt = String.format("""
            다음은 대전 시장의 코스 정보입니다:
            
            %s
            
            사용자가 선택한 키워드: %s
            
            위의 키워드와 코스 설명을 기반으로 가장 적합한 코스 하나를 선택해주세요.
            응답은 반드시 "코스 ID: [숫자]" 형식으로만 해주세요.
            예시: "코스 ID: 1"
            """, coursesInfo.toString(), String.join(", ", request.getTags()));
        
        		// OpenAI 서비스 생성
		OpenAiService openAiService = new OpenAiService(openaiApiKey);
		
		// AI 호출
		ChatCompletionRequest chatRequest = ChatCompletionRequest.builder()
				.model("gpt-3.5-turbo")
				.messages(List.of(new ChatMessage("user", prompt)))
				.build();
		
		String aiResponse = openAiService.createChatCompletion(chatRequest)
				.getChoices().get(0).getMessage().getContent();
        
        // AI 응답에서 코스 ID 추출
        String courseIdStr = aiResponse.replaceAll("[^0-9]", "");
        Long recommendedCourseId = Long.parseLong(courseIdStr);
        
        // 추천된 코스 반환
        Optional<Course> recommendedCourse = courses.stream()
                .filter(course -> course.getCourseId().equals(recommendedCourseId))
                .findFirst();
        
        if (recommendedCourse.isEmpty()) {
            // AI가 잘못된 ID를 반환한 경우 첫 번째 코스 반환
            return courseMapper.toDto(courses.get(0));
        }
        
        return courseMapper.toDto(recommendedCourse.get());
    }
}
