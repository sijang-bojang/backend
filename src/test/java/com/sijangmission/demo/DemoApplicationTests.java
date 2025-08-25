package com.sijangmission.demo;

import com.sijangmission.demo.controller.*;
import com.sijangmission.demo.domain.core.*;
import com.sijangmission.demo.domain.relation.*;
import com.sijangmission.demo.dto.*;
import com.sijangmission.demo.repository.*;
import com.sijangmission.demo.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DemoApplicationTests {

    @Autowired
    private MarketController marketController;
    @Autowired
    private SpotController spotController;
    @Autowired
    private CourseController courseController;
    @Autowired
    private MissionController missionController;
    @Autowired
    private UserController userController;

    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TypeRepository typeRepository;

    // ==================== 테스트 데이터 생성 ====================
    
    @Test
    void 테스트_데이터_생성_및_기본_CRUD_테스트() {
        // 1. 마켓 데이터 생성 및 테스트
        마켓_CRUD_테스트();
        
        // 2. 스팟 데이터 생성 및 테스트
        스팟_CRUD_테스트();
        
        // 3. 코스 데이터 생성 및 테스트
        코스_CRUD_테스트();
        
        // 4. 미션 데이터 생성 및 테스트
        미션_CRUD_테스트();
        
        // 5. 사용자 데이터 생성 및 테스트
        사용자_CRUD_테스트();
    }

    // ==================== 마켓 CRUD 테스트 ====================
    
    private void 마켓_CRUD_테스트() {
        System.out.println("=== 마켓 CRUD 테스트 시작 ===");
        
        // 마켓 생성
        Market market = new Market();
        market.setName("테스트 마켓");
        market.setDescription("테스트용 마켓입니다");
        market.setAddress("서울시 강남구 테스트로 123");
        market.setLatitude(37.5665);
        market.setLongitude(126.9780);
        
        Market savedMarket = marketRepository.save(market);
        assertNotNull(savedMarket.getMarketId());
        System.out.println("마켓 생성 성공: " + savedMarket.getName());
        
        // 마켓 조회 (Repository)
        Optional<Market> foundMarket = marketRepository.findById(savedMarket.getMarketId());
        assertTrue(foundMarket.isPresent());
        assertEquals("테스트 마켓", foundMarket.get().getName());
        
        // 마켓 조회 (Controller - DTO 변환 확인)
        ResponseEntity<MarketDto> response = marketController.getMarketById(savedMarket.getMarketId());
        assertEquals(200, response.getStatusCodeValue());
        MarketDto marketDto = response.getBody();
        assertNotNull(marketDto);
        assertEquals("테스트 마켓", marketDto.getName());
        assertEquals("테스트용 마켓입니다", marketDto.getDescription());
        System.out.println("마켓 DTO 변환 성공: " + marketDto.getName());
        
        // 마켓 수정
        savedMarket.setDescription("수정된 마켓 설명");
        Market updatedMarket = marketRepository.save(savedMarket);
        assertEquals("수정된 마켓 설명", updatedMarket.getDescription());
        
        // 마켓 삭제
        marketRepository.deleteById(savedMarket.getMarketId());
        Optional<Market> deletedMarket = marketRepository.findById(savedMarket.getMarketId());
        assertFalse(deletedMarket.isPresent());
        System.out.println("마켓 삭제 성공");
    }

    // ==================== 스팟 CRUD 테스트 ====================
    
    private void 스팟_CRUD_테스트() {
        System.out.println("=== 스팟 CRUD 테스트 시작 ===");
        
        // 마켓 먼저 생성
        Market market = new Market();
        market.setName("스팟 테스트용 마켓");
        market.setDescription("스팟 테스트용");
        market.setAddress("서울시 강남구");
        market.setLatitude(37.5665);
        market.setLongitude(126.9780);
        Market savedMarket = marketRepository.save(market);
        
        // 스팟 생성
        Spot spot = new Spot();
        spot.setMarket(savedMarket);
        spot.setName("테스트 스팟");
        spot.setCategory("카페");
        spot.setDescription("테스트용 카페입니다");
        spot.setLatitude(37.5665);
        spot.setLongitude(126.9780);
        
        Spot savedSpot = spotRepository.save(spot);
        assertNotNull(savedSpot.getSpotId());
        System.out.println("스팟 생성 성공: " + savedSpot.getName());
        
        // 스팟 조회 (Repository)
        Optional<Spot> foundSpot = spotRepository.findById(savedSpot.getSpotId());
        assertTrue(foundSpot.isPresent());
        assertEquals("테스트 스팟", foundSpot.get().getName());
        
        // 스팟 조회 (Controller - DTO 변환 확인)
        ResponseEntity<SpotDto> response = spotController.getSpotById(savedSpot.getSpotId());
        assertEquals(200, response.getStatusCodeValue());
        SpotDto spotDto = response.getBody();
        assertNotNull(spotDto);
        assertEquals("테스트 스팟", spotDto.getName());
        assertEquals("카페", spotDto.getCategory());
        System.out.println("스팟 DTO 변환 성공: " + spotDto.getName());
        
        // 스팟 수정
        savedSpot.setDescription("수정된 스팟 설명");
        Spot updatedSpot = spotRepository.save(savedSpot);
        assertEquals("수정된 스팟 설명", updatedSpot.getDescription());
        
        // 스팟 삭제
        spotRepository.deleteById(savedSpot.getSpotId());
        Optional<Spot> deletedSpot = spotRepository.findById(savedSpot.getSpotId());
        assertFalse(deletedSpot.isPresent());
        
        // 마켓도 삭제
        marketRepository.deleteById(savedMarket.getMarketId());
        System.out.println("스팟 삭제 성공");
    }

    // ==================== 코스 CRUD 테스트 ====================
    
    private void 코스_CRUD_테스트() {
        System.out.println("=== 코스 CRUD 테스트 시작 ===");
        
        // 마켓 먼저 생성
        Market market = new Market();
        market.setName("코스 테스트용 마켓");
        market.setDescription("코스 테스트용");
        market.setAddress("서울시 강남구");
        market.setLatitude(37.5665);
        market.setLongitude(126.9780);
        Market savedMarket = marketRepository.save(market);
        
        // 코스 생성
        Course course = new Course();
        course.setMarket(savedMarket);
        course.setName("테스트 코스");
        course.setDescription("테스트용 코스입니다");
        
        Course savedCourse = courseRepository.save(course);
        assertNotNull(savedCourse.getCourseId());
        System.out.println("코스 생성 성공: " + savedCourse.getName());
        
        // 코스 조회 (Repository)
        Optional<Course> foundCourse = courseRepository.findById(savedCourse.getCourseId());
        assertTrue(foundCourse.isPresent());
        assertEquals("테스트 코스", foundCourse.get().getName());
        
        // 코스 조회 (Controller - DTO 변환 확인)
        ResponseEntity<CourseDto> response = courseController.getCourseById(savedCourse.getCourseId());
        assertEquals(200, response.getStatusCodeValue());
        CourseDto courseDto = response.getBody();
        assertNotNull(courseDto);
        assertEquals("테스트 코스", courseDto.getName());
        assertEquals("테스트용 코스입니다", courseDto.getDescription());
        System.out.println("코스 DTO 변환 성공: " + courseDto.getName());
        
        // 코스 수정
        savedCourse.setDescription("수정된 코스 설명");
        Course updatedCourse = courseRepository.save(savedCourse);
        assertEquals("수정된 코스 설명", updatedCourse.getDescription());
        
        // 코스 삭제
        courseRepository.deleteById(savedCourse.getCourseId());
        Optional<Course> deletedCourse = courseRepository.findById(savedCourse.getCourseId());
        assertFalse(deletedCourse.isPresent());
        
        // 마켓도 삭제
        marketRepository.deleteById(savedMarket.getMarketId());
        System.out.println("코스 삭제 성공");
    }

    // ==================== 미션 CRUD 테스트 ====================
    
    private void 미션_CRUD_테스트() {
        System.out.println("=== 미션 CRUD 테스트 시작 ===");
        
        // 미션 생성
        Mission mission = new Mission();
        mission.setTitle("테스트 미션");
        mission.setDescription("테스트용 미션입니다");
        mission.setRewardPoints(100);
        mission.setMissionType("VISIT");
        
        Mission savedMission = missionRepository.save(mission);
        assertNotNull(savedMission.getMissionId());
        System.out.println("미션 생성 성공: " + savedMission.getTitle());
        
        // 미션 조회 (Repository)
        Optional<Mission> foundMission = missionRepository.findById(savedMission.getMissionId());
        assertTrue(foundMission.isPresent());
        assertEquals("테스트 미션", foundMission.get().getTitle());
        
        // 미션 조회 (Controller - DTO 변환 확인)
        ResponseEntity<MissionDto> response = missionController.getMissionById(savedMission.getMissionId());
        assertEquals(200, response.getStatusCodeValue());
        MissionDto missionDto = response.getBody();
        assertNotNull(missionDto);
        assertEquals("테스트 미션", missionDto.getTitle());
        assertEquals(100, missionDto.getRewardPoints());
        assertEquals("VISIT", missionDto.getMissionType());
        System.out.println("미션 DTO 변환 성공: " + missionDto.getTitle());
        
        // 미션 수정
        savedMission.setDescription("수정된 미션 설명");
        Mission updatedMission = missionRepository.save(savedMission);
        assertEquals("수정된 미션 설명", updatedMission.getDescription());
        
        // 미션 삭제
        missionRepository.deleteById(savedMission.getMissionId());
        Optional<Mission> deletedMission = missionRepository.findById(savedMission.getMissionId());
        assertFalse(deletedMission.isPresent());
        System.out.println("미션 삭제 성공");
    }

    // ==================== 사용자 CRUD 테스트 ====================
    
    private void 사용자_CRUD_테스트() {
        System.out.println("=== 사용자 CRUD 테스트 시작 ===");
        
        // 사용자 생성
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setTotalReward(0);
        user.setExp(0);
        
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getUserId());
        System.out.println("사용자 생성 성공: " + savedUser.getUsername());
        
        // 사용자 조회 (Repository)
        Optional<User> foundUser = userRepository.findById(savedUser.getUserId());
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
        
        // 사용자 조회 (Controller - DTO 변환 확인)
        ResponseEntity<UserDto> response = userController.getUserById(savedUser.getUserId());
        assertEquals(200, response.getStatusCodeValue());
        UserDto userDto = response.getBody();
        assertNotNull(userDto);
        assertEquals("testuser", userDto.getUsername());
        assertEquals(0, userDto.getTotalReward());
        assertEquals(0, userDto.getExp());
        System.out.println("사용자 DTO 변환 성공: " + userDto.getUsername());
        
        // 사용자 수정
        savedUser.setTotalReward(100);
        User updatedUser = userRepository.save(savedUser);
        assertEquals(100, updatedUser.getTotalReward());
        
        // 사용자 삭제
        userRepository.deleteById(savedUser.getUserId());
        Optional<User> deletedUser = userRepository.findById(savedUser.getUserId());
        assertFalse(deletedUser.isPresent());
        System.out.println("사용자 삭제 성공");
    }

    // ==================== 전체 목록 조회 테스트 ====================
    
    @Test
    void 전체_목록_조회_테스트() {
        System.out.println("=== 전체 목록 조회 테스트 시작 ===");
        
        // 테스트 데이터 생성
        createTestData();
        
        // 마켓 목록 조회
        ResponseEntity<List<MarketDto>> marketsResponse = marketController.getAllMarkets();
        assertEquals(200, marketsResponse.getStatusCodeValue());
        List<MarketDto> markets = marketsResponse.getBody();
        assertNotNull(markets);
        assertTrue(markets.size() > 0);
        System.out.println("마켓 목록 조회 성공: " + markets.size() + "개");
        
        // 스팟 목록 조회
        ResponseEntity<List<SpotDto>> spotsResponse = spotController.getAllSpots();
        assertEquals(200, spotsResponse.getStatusCodeValue());
        List<SpotDto> spots = spotsResponse.getBody();
        assertNotNull(spots);
        assertTrue(spots.size() > 0);
        System.out.println("스팟 목록 조회 성공: " + spots.size() + "개");
        
        // 코스 목록 조회
        ResponseEntity<List<CourseDto>> coursesResponse = courseController.getAllCourses();
        assertEquals(200, coursesResponse.getStatusCodeValue());
        List<CourseDto> courses = coursesResponse.getBody();
        assertNotNull(courses);
        assertTrue(courses.size() > 0);
        System.out.println("코스 목록 조회 성공: " + courses.size() + "개");
        
        // 미션 목록 조회
        ResponseEntity<List<MissionDto>> missionsResponse = missionController.getAllMissions();
        assertEquals(200, missionsResponse.getStatusCodeValue());
        List<MissionDto> missions = missionsResponse.getBody();
        assertNotNull(missions);
        assertTrue(missions.size() > 0);
        System.out.println("미션 목록 조회 성공: " + missions.size() + "개");
        
        // 사용자 목록 조회
        ResponseEntity<List<UserDto>> usersResponse = userController.getAllUsers();
        assertEquals(200, usersResponse.getStatusCodeValue());
        List<UserDto> users = usersResponse.getBody();
        assertNotNull(users);
        assertTrue(users.size() > 0);
        System.out.println("사용자 목록 조회 성공: " + users.size() + "개");
        
        // 테스트 데이터 정리
        cleanupTestData();
    }

    // ==================== 테스트 데이터 생성 헬퍼 메서드 ====================
    
    private void createTestData() {
        // 마켓 생성
        Market market = new Market();
        market.setName("테스트 마켓");
        market.setDescription("테스트용 마켓");
        market.setAddress("서울시 강남구");
        market.setLatitude(37.5665);
        market.setLongitude(126.9780);
        marketRepository.save(market);
        
        // 스팟 생성
        Spot spot = new Spot();
        spot.setMarket(market);
        spot.setName("테스트 스팟");
        spot.setCategory("카페");
        spot.setDescription("테스트용 카페");
        spot.setLatitude(37.5665);
        spot.setLongitude(126.9780);
        spotRepository.save(spot);
        
        // 코스 생성
        Course course = new Course();
        course.setMarket(market);
        course.setName("테스트 코스");
        course.setDescription("테스트용 코스");
        courseRepository.save(course);
        
        // 미션 생성
        Mission mission = new Mission();
        mission.setTitle("테스트 미션");
        mission.setDescription("테스트용 미션");
        mission.setRewardPoints(100);
        mission.setMissionType("VISIT");
        missionRepository.save(mission);
        
        // 사용자 생성
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setTotalReward(0);
        user.setExp(0);
        userRepository.save(user);
    }

    // ==================== 테스트 데이터 정리 헬퍼 메서드 ====================
    
    private void cleanupTestData() {
        userRepository.deleteAll();
        missionRepository.deleteAll();
        courseRepository.deleteAll();
        spotRepository.deleteAll();
        marketRepository.deleteAll();
    }
}
