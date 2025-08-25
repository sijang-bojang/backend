package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Course;
import com.sijangmission.demo.dto.CourseDto;
import com.sijangmission.demo.dto.CourseRecommendationRequest;
import com.sijangmission.demo.dto.CourseRecommendationResponse;
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
    
    	public CourseRecommendationResponse recommendCourse(CourseRecommendationRequest request) {
		try {
			// 해당 시장의 모든 코스 조회
			List<Course> courses = courseRepository.findByMarketMarketId(request.getMarketId());
			
			if (courses.isEmpty()) {
				throw new RuntimeException("해당 시장에 코스가 없습니다.");
			}
			
			// API 키 검증 (AI 강제 사용)
			if (openaiApiKey == null || openaiApiKey.equals("your-openai-api-key-here") || openaiApiKey.isEmpty()) {
				throw new RuntimeException("OpenAI API 키가 설정되지 않았습니다. AI 추천 기능을 사용할 수 없습니다.");
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
			
			try {
				System.out.println("=== AI 추천 시작 ===");
				System.out.println("API 키 확인: " + (openaiApiKey != null ? "설정됨" : "설정되지 않음"));
				System.out.println("프롬프트: " + prompt);
				
				// OpenAI 서비스 생성
				OpenAiService openAiService = new OpenAiService(openaiApiKey);
				
				// AI 호출
				ChatCompletionRequest chatRequest = ChatCompletionRequest.builder()
						.model("gpt-3.5-turbo")
						.messages(List.of(new ChatMessage("user", prompt)))
						.build();
				
				System.out.println("OpenAI API 호출 중...");
				String aiResponse = openAiService.createChatCompletion(chatRequest)
						.getChoices().get(0).getMessage().getContent();
				
				System.out.println("AI 응답: " + aiResponse);
				
				// AI 응답에서 코스 ID 추출 (더 안전한 방법)
				String courseIdStr = aiResponse.replaceAll("[^0-9]", "");
				if (courseIdStr.isEmpty()) {
					System.err.println("AI 응답에서 코스 ID를 추출할 수 없습니다: " + aiResponse);
					return getDefaultRecommendation(courses, request.getTags());
				}
				
				Long recommendedCourseId = Long.parseLong(courseIdStr);
				
				// 추천된 코스 찾기
				Optional<Course> recommendedCourse = courses.stream()
						.filter(course -> course.getCourseId().equals(recommendedCourseId))
						.findFirst();
				
				if (recommendedCourse.isPresent()) {
					Course finalCourse = recommendedCourse.get();
					return CourseRecommendationResponse.builder()
							.courseId(finalCourse.getCourseId())
							.courseName(finalCourse.getName())
							.description(finalCourse.getDescription())
							.marketName(finalCourse.getMarket() != null ? finalCourse.getMarket().getName() : null)
							.recommendationReason("AI가 선택한 키워드: " + String.join(", ", request.getTags()) + "와 가장 적합한 코스입니다.")
							.confidenceScore(0.85)
							.build();
				} else {
					System.err.println("AI가 추천한 코스 ID " + recommendedCourseId + "가 존재하지 않습니다.");
					return getDefaultRecommendation(courses, request.getTags());
				}
				
			} catch (Exception aiException) {
				System.err.println("AI 호출 중 오류 발생: " + aiException.getMessage());
				aiException.printStackTrace();
				// AI 오류 시 기본 추천 로직 사용
				return getDefaultRecommendation(courses, request.getTags());
			}
			
		} catch (Exception e) {
			System.err.println("CourseService recommendation error: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	// 기본 추천 로직 (AI 없이)
	private CourseRecommendationResponse getDefaultRecommendation(List<Course> courses, List<String> tags) {
		// 간단한 키워드 매칭 로직
		Course bestMatch = courses.get(0); // 기본값
		int bestScore = 0;
		
		for (Course course : courses) {
			int score = 0;
			String courseText = (course.getName() + " " + course.getDescription()).toLowerCase();
			
			for (String tag : tags) {
				if (courseText.contains(tag.toLowerCase())) {
					score++;
				}
			}
			
			if (score > bestScore) {
				bestScore = score;
				bestMatch = course;
			}
		}
		
		return CourseRecommendationResponse.builder()
				.courseId(bestMatch.getCourseId())
				.courseName(bestMatch.getName())
				.description(bestMatch.getDescription())
				.marketName(bestMatch.getMarket() != null ? bestMatch.getMarket().getName() : null)
				.recommendationReason("키워드 매칭 결과: " + String.join(", ", tags) + "와 가장 적합한 코스입니다.")
				.confidenceScore(0.7)
				.build();
	}
}
