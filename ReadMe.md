# 시장 미션 백엔드 프로젝트

JPA 기반의 Spring Boot 애플리케이션으로, 시장 탐방과 미션 수행을 관리하는 시스템입니다.

## 📁 프로젝트 구조

```
src/main/java/com/sijangmission/demo/
├── domain/          # JPA 엔티티
│   ├── core/        # 핵심 엔티티 (7개)
│   └── relation/    # 연결 엔티티 (5개)
├── dto/             # 데이터 전송 객체 (8개)
├── mapper/          # 엔티티-DTO 변환 매퍼 (5개)
├── repository/      # 데이터 접근 계층 (9개)
├── service/         # 비즈니스 로직 계층 (7개)
├── controller/      # REST API 계층 (7개)
└── DemoApplication.java
```

## 🏗️ 생성된 프로젝트 구조 요약

### 📦 Domain 패키지 (12개 엔티티)
- **core/**: 핵심 엔티티 (7개) - `Market`, `Course`, `Spot`, `Image`, `Type`, `Mission`, `User`
- **relation/**: 연결 엔티티 (5개) - `CourseSpot`, `CourseType`, `SpotMission`, `UserMission`, `UserCourseProgress`

### 📦 Repository 패키지 (9개 Repository)
- 각 도메인별 JPA Repository 인터페이스
- Spring Data JPA의 기본 CRUD 메서드 + 커스텀 쿼리 메서드

### 📦 DTO 패키지 (8개 DTO)
- **MarketDto**: 시장 정보 (코스 수, 스팟 수 포함)
- **SpotDto**: 스팟 정보 (시장명, 미션 수, 코스명들 포함)
- **CourseDto**: 코스 정보 (시장명, 타입명들, 스팟들, 가족/연인 코스 여부 포함)
- **CourseSpotDto**: 코스-스팟 연결 정보 (순서 포함)
- **MissionDto**: 미션 정보 (스팟명들, VISIT/NON_VISIT 여부 포함)
- **UserDto**: 사용자 정보 (완료한 미션/코스 수 포함)
- **UserMissionDto**: 사용자 미션 진행 상황
- **UserCourseProgressDto**: 사용자 코스 진행 상황

### 📦 Mapper 패키지 (5개 Mapper)
- 엔티티를 DTO로 변환하는 매퍼 클래스들
- 순환 참조 문제 해결
- API 응답 구조 최적화

### 📦 Service 패키지 (7개 Service)
- 비즈니스 로직 처리
- `@Transactional` 어노테이션으로 트랜잭션 관리
- 엔티티를 DTO로 변환하여 반환
- 사용자 미션 시작/완료, 코스 진행 등 특별한 비즈니스 로직 포함

### 📦 Controller 패키지 (7개 Controller)
- RESTful API 엔드포인트 제공
- HTTP 메서드별 CRUD 작업 지원
- DTO만 응답으로 반환 (순환 참조 방지)
- 검색 기능 포함

## 🔗 주요 매핑 관계

### 1. Market (시장) - 핵심 엔티티
- **Market ↔ Course**: 1:N (하나의 시장에 여러 코스)
- **Market ↔ Spot**: 1:N (하나의 시장에 여러 스팟)

### 2. Course (코스) - 탐방 경로
- **Course ↔ Spot**: N:M (CourseSpot 연결 테이블)
- **Course ↔ Type**: N:M (CourseType 연결 테이블) - 가족/연인 코스 분류
- **Course ↔ User**: N:M (UserCourseProgress 연결 테이블)

### 3. Spot (스팟) - 방문 지점
- **Spot ↔ Mission**: N:M (SpotMission 연결 테이블)
- **Spot ↔ Image**: N:1 (하나의 이미지에 여러 스팟)

### 4. User (사용자) - 시스템 사용자
- **User ↔ Mission**: N:M (UserMission 연결 테이블)
- **User ↔ Course**: N:M (UserCourseProgress 연결 테이블)

## 📋 클래스별 상세 설명

### 🏪 Market (시장)
**설명**: 실제 시장 정보를 담는 핵심 엔티티
**주요 필드**:
- `marketId`: 시장 고유 ID (PK)
- `name`: 시장명
- `address`: 시장 주소
- `latitude/longitude`: GPS 좌표
- `description`: 시장 설명

**관계**:
- `courses`: 해당 시장의 코스 목록 (1:N)
- `spots`: 해당 시장의 스팟 목록 (1:N)

### 🛤️ Course (코스)
**설명**: 시장 내 탐방 경로를 정의하는 엔티티 (가족/연인 코스 분류 지원)
**주요 필드**:
- `courseId`: 코스 고유 ID (PK)
- `market`: 소속 시장 (FK)
- `name`: 코스명
- `description`: 코스 설명

**관계**:
- `market`: 소속 시장 (N:1)
- `courseSpots`: 코스에 포함된 스팟들 (N:M)
- `courseTypes`: 코스 관련 타입들 (N:M)
- `userCourseProgresses`: 사용자별 코스 진행 상황 (N:M)

**편의 메서드**:
- `getTypes()`: 코스에 속한 타입들 조회
- `hasType(String typeName)`: 특정 타입인지 확인
- `isFamilyCourse()`: 가족 코스인지 확인
- `isCoupleCourse()`: 연인 코스인지 확인

### 📍 Spot (스팟)
**설명**: 시장 내 방문 지점 정보
**주요 필드**:
- `spotId`: 스팟 고유 ID (PK)
- `market`: 소속 시장 (FK)
- `name`: 스팟명
- `category`: 스팟 카테고리
- `description`: 스팟 설명
- `image`: 관련 이미지 (FK)
- `latitude/longitude`: GPS 좌표

**관계**:
- `market`: 소속 시장 (N:1)
- `image`: 관련 이미지 (N:1)
- `courseSpots`: 해당 스팟이 포함된 코스들 (N:M)
- `spotMissions`: 해당 스팟의 미션들 (N:M)

### 🖼️ Image (이미지)
**설명**: 스팟 관련 이미지 정보
**주요 필드**:
- `imageId`: 이미지 고유 ID (PK)
- `imageUrl`: 이미지 URL

**관계**:
- `spots`: 해당 이미지를 사용하는 스팟들 (1:N)

### 🏷️ Type (타입)
**설명**: 코스의 분류 타입 (가족/연인 코스 분류)
**주요 필드**:
- `typeId`: 타입 고유 ID (PK)
- `name`: 타입명 ("가족이랑 가기 좋은 코스", "연인과 가기 좋은 코스")

**관계**:
- `courseTypes`: 해당 타입이 적용된 코스들 (N:M)

**편의 메서드**:
- `getCourses()`: 타입에 속한 코스들 조회
- `isFamilyType()`: 가족 코스 타입인지 확인
- `isCoupleType()`: 연인 코스 타입인지 확인

### 🎯 Mission (미션)
**설명**: 사용자가 수행할 미션 정보
**주요 필드**:
- `missionId`: 미션 고유 ID (PK)
- `title`: 미션 제목
- `description`: 미션 설명
- `rewardPoints`: 보상 포인트
- `missionType`: 미션 타입

**관계**:
- `spotMissions`: 해당 미션이 수행되는 스팟들 (N:M)
- `userMissions`: 사용자별 미션 수행 상황 (N:M)

### 👤 User (사용자)
**설명**: 시스템 사용자 정보
**주요 필드**:
- `userId`: 사용자 고유 ID (PK)
- `username`: 사용자명 (unique)
- `email`: 이메일 (unique)
- `password`: 비밀번호
- `totalReward`: 총 보상 포인트
- `exp`: 경험치

**관계**:
- `userMissions`: 사용자의 미션 수행 기록 (N:M)
- `userCourseProgresses`: 사용자의 코스 진행 상황 (N:M)

## 🔗 연결 엔티티 (Junction Tables)

### 📍 CourseSpot
**설명**: 코스와 스팟의 N:M 관계를 관리
**주요 필드**:
- `course`: 코스 (FK)
- `spot`: 스팟 (FK)
- `stepNumber`: 코스 내 스팟 순서

### 🏷️ CourseType
**설명**: 코스와 타입의 N:M 관계를 관리 (가족/연인 코스 분류)
**주요 필드**:
- `course`: 코스 (FK)
- `type`: 타입 (FK)

### 🎯 SpotMission
**설명**: 스팟과 미션의 N:M 관계를 관리
**주요 필드**:
- `spot`: 스팟 (FK)
- `mission`: 미션 (FK)

### 👤 UserMission
**설명**: 사용자와 미션의 N:M 관계를 관리하며 진행 상황 추적
**주요 필드**:
- `userMissionId`: 사용자 미션 고유 ID (PK)
- `user`: 사용자 (FK)
- `mission`: 미션 (FK)
- `status`: 미션 상태 (IN_PROGRESS, COMPLETED 등)
- `startedAt`: 시작 시간
- `completedAt`: 완료 시간

### 📈 UserCourseProgress
**설명**: 사용자와 코스의 N:M 관계를 관리하며 진행 상황 추적
**주요 필드**:
- `user`: 사용자 (FK)
- `course`: 코스 (FK)
- `currentStep`: 현재 진행 단계
- `status`: 코스 상태 (IN_PROGRESS, COMPLETED 등)
- `startedAt`: 시작 시간
- `completedAt`: 완료 시간

## 🚀 API 엔드포인트

### Markets
- `GET /api/markets` - 모든 시장 조회 (MarketDto 반환)
- `GET /api/markets/{marketId}` - 특정 시장 조회 (MarketDto 반환)
- `GET /api/markets/search` - 시장 검색 (MarketDto 리스트 반환)
- `POST /api/markets` - 시장 생성 (MarketDto 반환)
- `PUT /api/markets/{marketId}` - 시장 수정 (MarketDto 반환)
- `DELETE /api/markets/{marketId}` - 시장 삭제

### Courses
- `GET /api/courses` - 모든 코스 조회 (CourseDto 반환)
- `GET /api/courses/{courseId}` - 특정 코스 조회 (CourseDto 반환)
- `GET /api/courses/market/{marketId}` - 시장별 코스 조회 (CourseDto 리스트 반환)
- `GET /api/courses/search` - 코스 검색 (CourseDto 리스트 반환)
- `POST /api/courses` - 코스 생성 (CourseDto 반환)
- `PUT /api/courses/{courseId}` - 코스 수정 (CourseDto 반환)
- `DELETE /api/courses/{courseId}` - 코스 삭제

### Users
- `GET /api/users` - 모든 사용자 조회 (UserDto 반환)
- `GET /api/users/{userId}` - 특정 사용자 조회 (UserDto 반환)
- `GET /api/users/username/{username}` - 사용자명으로 조회 (UserDto 반환)
- `GET /api/users/email/{email}` - 이메일로 조회 (UserDto 반환)
- `POST /api/users` - 사용자 생성 (UserDto 반환)
- `PUT /api/users/{userId}` - 사용자 수정 (UserDto 반환)
- `PUT /api/users/{userId}/reward` - 보상 포인트 업데이트 (UserDto 반환)
- `PUT /api/users/{userId}/exp` - 경험치 업데이트 (UserDto 반환)
- `DELETE /api/users/{userId}` - 사용자 삭제

### Missions
- `GET /api/missions` - 모든 미션 조회 (MissionDto 반환)
- `GET /api/missions/{missionId}` - 특정 미션 조회 (MissionDto 반환)
- `GET /api/missions/type/{missionType}` - 타입별 미션 조회 (MissionDto 리스트 반환)
- `GET /api/missions/search` - 미션 검색 (MissionDto 리스트 반환)
- `POST /api/missions` - 미션 생성 (MissionDto 반환)
- `PUT /api/missions/{missionId}` - 미션 수정 (MissionDto 반환)
- `DELETE /api/missions/{missionId}` - 미션 삭제

### User Missions
- `GET /api/user-missions` - 모든 사용자 미션 조회 (UserMissionDto 반환)
- `GET /api/user-missions/user/{userId}` - 사용자별 미션 조회 (UserMissionDto 리스트 반환)
- `GET /api/user-missions/user/{userId}/status/{status}` - 상태별 미션 조회 (UserMissionDto 리스트 반환)
- `POST /api/user-missions/start` - 미션 시작 (UserMissionDto 반환)
- `POST /api/user-missions/complete` - 미션 완료 (UserMissionDto 반환)

### User Course Progress
- `GET /api/user-course-progress` - 모든 코스 진행 상황 조회 (UserCourseProgressDto 반환)
- `GET /api/user-course-progress/user/{userId}` - 사용자별 코스 진행 상황 (UserCourseProgressDto 리스트 반환)
- `POST /api/user-course-progress/start` - 코스 시작 (UserCourseProgressDto 반환)
- `PUT /api/user-course-progress/progress` - 코스 진행 단계 업데이트 (UserCourseProgressDto 반환)
- `POST /api/user-course-progress/complete` - 코스 완료 (UserCourseProgressDto 반환)

## 🛠️ 기술 스택

- **Framework**: Spring Boot
- **ORM**: JPA/Hibernate
- **Database**: JPA 지원 데이터베이스 (MySQL, PostgreSQL, H2 등)
- **Build Tool**: Gradle
- **Language**: Java
- **Lombok**: 코드 간소화
- **Architecture**: DTO 패턴, 계층형 아키텍처

## 📊 데이터 구조

### 코스 분류 시스템
- **가족이랑 가기 좋은 코스**: 가족 단위로 즐기기 좋은 코스
- **연인과 가기 좋은 코스**: 연인과 함께하기 좋은 로맨틱한 코스

### 시장별 코스 구성
- **대전 중앙 시장**: 맛집 탐방 코스 (가족), 문화 체험 코스 (연인)
- **대전 문창 시장**: 떡 맛집 코스 (가족), 야구 관람 코스 (가족)
- **대전 역전 시장**: 로컬 맛집 코스 (가족), 히든 스팟 코스 (연인)

## 🔄 DTO 패턴 구조

### 엔티티 → DTO 변환 흐름
```
Controller → Service → Repository → Entity
    ↓
Controller ← Service ← Mapper ← Entity
    ↓
Controller → Client (DTO 반환)
```

### 주요 DTO 특징
- **순환 참조 방지**: 엔티티 간 순환 참조 문제 해결
- **API 최적화**: 필요한 데이터만 전송
- **유지보수성**: 엔티티 변경이 API에 직접 영향 없음
- **명확한 계약**: API 응답 구조 명확화

## 📝 주요 특징

1. **계층별 분리**: Domain, Repository, Service, Controller 계층으로 명확한 책임 분리
2. **JPA 매핑**: ERD 기반의 정확한 엔티티 관계 매핑
3. **DTO 패턴**: 엔티티와 API 응답을 분리하여 순환 참조 문제 해결
4. **RESTful API**: 표준 HTTP 메서드를 활용한 REST API 설계
5. **트랜잭션 관리**: Service 계층에서 `@Transactional` 어노테이션으로 트랜잭션 관리
6. **검색 기능**: 다양한 조건으로 데이터 검색 가능
7. **진행 상황 추적**: 사용자의 미션 및 코스 진행 상황을 상세히 추적
8. **코스 분류 시스템**: 가족/연인 코스 분류를 통한 맞춤형 추천 기능
9. **편의 메서드**: 코스 타입 확인 및 조회를 위한 편의 메서드 제공
10. **매퍼 패턴**: 엔티티-DTO 변환을 위한 전용 매퍼 클래스 제공
