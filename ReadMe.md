# 시장 미션 백엔드 프로젝트

JPA 기반의 Spring Boot 애플리케이션으로, 시장 탐방과 미션 수행을 관리하는 시스템입니다.

## 📁 프로젝트 구조

```
src/main/java/com/sijangmission/demo/
├── domain/          # JPA 엔티티
│   ├── core/        # 핵심 엔티티 (7개)
│   └── relation/    # 연결 엔티티 (5개)
├── repository/      # 데이터 접근 계층 (9개)
├── service/         # 비즈니스 로직 계층 (7개)
├── controller/      # REST API 계층 (7개)
└── DemoApplication.java
```

## 🏗️ 생성된 프로젝트 구조 요약

### 📦 Domain 패키지 (12개 엔티티)
- **core/**: 핵심 엔티티 (7개) - `Market`, `Course`, `Spot`, `Image`, `Type`, `Mission`, `User`
- **relation/**: 연결 엔티티 (5개) - `CourseSpot`, `SpotType`, `SpotMission`, `UserMission`, `UserCourseProgress`

### 📦 Repository 패키지 (9개 Repository)
- 각 도메인별 JPA Repository 인터페이스
- Spring Data JPA의 기본 CRUD 메서드 + 커스텀 쿼리 메서드

### 📦 Service 패키지 (7개 Service)
- 비즈니스 로직 처리
- `@Transactional` 어노테이션으로 트랜잭션 관리
- 사용자 미션 시작/완료, 코스 진행 등 특별한 비즈니스 로직 포함

### 📦 Controller 패키지 (7개 Controller)
- RESTful API 엔드포인트 제공
- HTTP 메서드별 CRUD 작업 지원
- 검색 기능 포함

## 🔗 주요 매핑 관계

### 1. Market (시장) - 핵심 엔티티
- **Market ↔ Course**: 1:N (하나의 시장에 여러 코스)
- **Market ↔ Spot**: 1:N (하나의 시장에 여러 스팟)

### 2. Course (코스) - 탐방 경로
- **Course ↔ Spot**: N:M (CourseSpot 연결 테이블)
- **Course ↔ Type**: N:M (SpotType 연결 테이블)
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
**설명**: 시장 내 탐방 경로를 정의하는 엔티티
**주요 필드**:
- `courseId`: 코스 고유 ID (PK)
- `market`: 소속 시장 (FK)
- `name`: 코스명
- `description`: 코스 설명

**관계**:
- `market`: 소속 시장 (N:1)
- `courseSpots`: 코스에 포함된 스팟들 (N:M)
- `spotTypes`: 코스 관련 타입들 (N:M)
- `userCourseProgresses`: 사용자별 코스 진행 상황 (N:M)

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
**설명**: 스팟이나 코스의 분류 타입
**주요 필드**:
- `typeId`: 타입 고유 ID (PK)
- `name`: 타입명

**관계**:
- `spotTypes`: 해당 타입이 적용된 스팟들 (N:M)

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

### 🏷️ SpotType
**설명**: 스팟과 타입의 N:M 관계를 관리
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
- `GET /api/markets` - 모든 시장 조회
- `GET /api/markets/{marketId}` - 특정 시장 조회
- `GET /api/markets/search` - 시장 검색
- `POST /api/markets` - 시장 생성
- `PUT /api/markets/{marketId}` - 시장 수정
- `DELETE /api/markets/{marketId}` - 시장 삭제

### Courses
- `GET /api/courses` - 모든 코스 조회
- `GET /api/courses/{courseId}` - 특정 코스 조회
- `GET /api/courses/market/{marketId}` - 시장별 코스 조회
- `GET /api/courses/search` - 코스 검색
- `POST /api/courses` - 코스 생성
- `PUT /api/courses/{courseId}` - 코스 수정
- `DELETE /api/courses/{courseId}` - 코스 삭제

### Users
- `GET /api/users` - 모든 사용자 조회
- `GET /api/users/{userId}` - 특정 사용자 조회
- `GET /api/users/username/{username}` - 사용자명으로 조회
- `GET /api/users/email/{email}` - 이메일로 조회
- `POST /api/users` - 사용자 생성
- `PUT /api/users/{userId}` - 사용자 수정
- `PUT /api/users/{userId}/reward` - 보상 포인트 업데이트
- `PUT /api/users/{userId}/exp` - 경험치 업데이트
- `DELETE /api/users/{userId}` - 사용자 삭제

### Missions
- `GET /api/missions` - 모든 미션 조회
- `GET /api/missions/{missionId}` - 특정 미션 조회
- `GET /api/missions/type/{missionType}` - 타입별 미션 조회
- `GET /api/missions/search` - 미션 검색
- `POST /api/missions` - 미션 생성
- `PUT /api/missions/{missionId}` - 미션 수정
- `DELETE /api/missions/{missionId}` - 미션 삭제

### User Missions
- `GET /api/user-missions` - 모든 사용자 미션 조회
- `GET /api/user-missions/user/{userId}` - 사용자별 미션 조회
- `GET /api/user-missions/user/{userId}/status/{status}` - 상태별 미션 조회
- `POST /api/user-missions/start` - 미션 시작
- `POST /api/user-missions/complete` - 미션 완료

### User Course Progress
- `GET /api/user-course-progress` - 모든 코스 진행 상황 조회
- `GET /api/user-course-progress/user/{userId}` - 사용자별 코스 진행 상황
- `POST /api/user-course-progress/start` - 코스 시작
- `PUT /api/user-course-progress/progress` - 코스 진행 단계 업데이트
- `POST /api/user-course-progress/complete` - 코스 완료

## 🛠️ 기술 스택

- **Framework**: Spring Boot
- **ORM**: JPA/Hibernate
- **Database**: JPA 지원 데이터베이스 (MySQL, PostgreSQL, H2 등)
- **Build Tool**: Gradle
- **Language**: Java
- **Lombok**: 코드 간소화

## 📝 주요 특징

1. **계층별 분리**: Domain, Repository, Service, Controller 계층으로 명확한 책임 분리
2. **JPA 매핑**: ERD 기반의 정확한 엔티티 관계 매핑
3. **RESTful API**: 표준 HTTP 메서드를 활용한 REST API 설계
4. **트랜잭션 관리**: Service 계층에서 `@Transactional` 어노테이션으로 트랜잭션 관리
5. **검색 기능**: 다양한 조건으로 데이터 검색 가능
6. **진행 상황 추적**: 사용자의 미션 및 코스 진행 상황을 상세히 추적
