# 시장미션 백엔드 API 명세서

## 📋 개요
- **Base URL**: `http://3.34.186.143:8080`
- **Content-Type**: `application/json`
- **인증**: 현재 인증 없음 (모든 API 공개)

---

## 🏪 1. 시장 (Market) API

### 1.1 모든 시장 조회
```http
GET /api/markets
```

**응답 예시:**
```json
[
  {
    "marketId": 1,
    "name": "대전 중앙 시장",
    "description": "대전의 대표 전통시장",
    "address": "대전광역시 중구 중앙로 123",
    "latitude": 36.3308,
    "longitude": 127.4325,
    "courseCount": 3,
    "spotCount": 15
  }
]
```

### 1.2 특정 시장 조회
```http
GET /api/markets/{marketId}
```

### 1.3 시장 생성
```http
POST /api/markets
Content-Type: application/json

{
  "name": "새로운 시장",
  "description": "시장 설명",
  "address": "주소",
  "latitude": 36.3308,
  "longitude": 127.4325
}
```

---

## 🎯 2. 미션 (Mission) API

### 2.1 모든 미션 조회
```http
GET /api/missions
```

**응답 예시:**
```json
[
  {
    "missionId": 1,
    "title": "생활의 달인 만두국 인증",
    "description": "개천식당의 대표 메뉴, 슴슴하고 담백한 이북식 만두국을 주문하고 인증샷을 찍어보세요.",
    "rewardPoints": 100,
    "missionType": "VISIT",
    "spotNames": ["개천식당"],
    "isVisitType": true,
    "isNonVisitType": false
  }
]
```

### 2.2 특정 미션 조회
```http
GET /api/missions/{missionId}
```

### 2.3 타입별 미션 조회
```http
GET /api/missions/type/{missionType}
```
- `missionType`: `VISIT`, `NON_VISIT`

### 2.4 미션 검색
```http
GET /api/missions/search?title={title}&minRewardPoints={points}
```

---

## 📍 3. 스팟 (Spot) API

### 3.1 모든 스팟 조회
```http
GET /api/spots
```

**응답 예시:**
```json
[
  {
    "spotId": 1,
    "marketId": 1,
    "marketName": "대전 중앙 시장",
    "name": "개천식당",
    "description": "전통 이북식 만두국 전문점",
    "category": "맛집",
    "latitude": 36.3308,
    "longitude": 127.4325,
    "imageUrl": "https://example.com/image.jpg",
    "missionCount": 2,
    "courseNames": ["맛집 탐방 코스", "전통 음식 코스"]
  }
]
```

### 3.2 특정 스팟 조회
```http
GET /api/spots/{spotId}
```

### 3.3 시장별 스팟 조회
```http
GET /api/spots/market/{marketId}
```

### 3.4 카테고리별 스팟 조회
```http
GET /api/spots/category/{category}
```

### 3.5 스팟 검색
```http
GET /api/spots/search?name={name}&category={category}
```

---

## 🗺️ 4. 코스 (Course) API

### 4.1 모든 코스 조회
```http
GET /api/courses
```

**응답 예시:**
```json
[
  {
    "courseId": 1,
    "marketId": 1,
    "marketName": "대전 중앙 시장",
    "name": "맛집 탐방 코스",
    "description": "대전 중앙시장의 맛집들을 둘러보는 코스",
    "typeNames": ["맛집", "전통"],
    "spots": [
      {
        "spotId": 1,
        "spotName": "개천식당",
        "stepNumber": 1
      }
    ],
    "isFamilyCourse": false,
    "isCoupleCourse": true
  }
]
```

### 4.2 특정 코스 조회
```http
GET /api/courses/{courseId}
```

### 4.3 시장별 코스 조회
```http
GET /api/courses/market/{marketId}
```

### 4.4 타입별 코스 조회
```http
GET /api/courses/type/{typeName}
```

---

## 👤 5. 사용자 (User) API

### 5.1 모든 사용자 조회
```http
GET /api/users
```

**응답 예시:**
```json
[
  {
    "userId": 1,
    "username": "엄태은",
    "email": "xodms3320@o.cnu.ac.kr",
    "totalReward": 500,
    "exp": 1000,
    "completedMissionCount": 5,
    "completedCourseCount": 2,
    "completeStamp": 3
  }
]
```

### 5.2 특정 사용자 조회
```http
GET /api/users/{userId}
```

### 5.3 사용자명으로 조회
```http
GET /api/users/username/{username}
```

### 5.4 이메일로 조회
```http
GET /api/users/email/{email}
```

### 5.5 사용자 생성
```http
POST /api/users
Content-Type: application/json

{
  "username": "새사용자",
  "email": "user@example.com",
  "password": "password123"
}
```

### 5.6 사용자 수정
```http
PUT /api/users/{userId}
Content-Type: application/json

{
  "username": "수정된사용자",
  "email": "updated@example.com",
  "password": "newpassword123"
}
```

### 5.7 보상 포인트 업데이트
```http
PUT /api/users/{userId}/reward
Content-Type: application/json

{
  "totalReward": 1000
}
```

### 5.8 경험치 업데이트
```http
PUT /api/users/{userId}/exp
Content-Type: application/json

{
  "exp": 1500
}
```

---

## 🎯 6. 사용자 미션 (UserMission) API

### 6.1 모든 사용자 미션 조회
```http
GET /api/user-missions
```

**응답 예시:**
```json
[
  {
    "userMissionId": 1,
    "userId": 1,
    "userName": "엄태은",
    "missionId": 1,
    "missionTitle": "생활의 달인 만두국 인증",
    "status": "IN_PROGRESS",
    "startedAt": "2025-08-24T04:58:50.27409",
    "completedAt": null,
    "completed": false,
    "inProgress": true
  }
]
```

### 6.2 특정 사용자 미션 조회
```http
GET /api/user-missions/{userMissionId}
```

### 6.3 사용자별 미션 조회
```http
GET /api/user-missions/user/{userId}
```

### 6.4 사용자별 상태별 미션 조회
```http
GET /api/user-missions/user/{userId}/status/{status}
```
- `status`: `NOT_STARTED`, `IN_PROGRESS`, `COMPLETED`

### 6.5 특정 사용자의 특정 미션 조회
```http
GET /api/user-missions/user/{userId}/mission/{missionId}
```

### 6.6 미션 시작 ⭐
```http
POST /api/user-missions/start?userId={userId}&missionId={missionId}
```

**응답 예시:**
```json
{
  "userMissionId": 1,
  "userId": 1,
  "userName": "엄태은",
  "missionId": 1,
  "missionTitle": "생활의 달인 만두국 인증",
  "status": "IN_PROGRESS",
  "startedAt": "2025-08-24T04:58:50.27409",
  "completedAt": null,
  "completed": false,
  "inProgress": true
}
```

### 6.7 미션 완료 ⭐
```http
POST /api/user-missions/complete?userId={userId}&missionId={missionId}
```

**응답 예시:**
```json
{
  "userMissionId": 1,
  "userId": 1,
  "userName": "엄태은",
  "missionId": 1,
  "missionTitle": "생활의 달인 만두국 인증",
  "status": "COMPLETED",
  "startedAt": "2025-08-24T04:58:50.27409",
  "completedAt": "2025-08-24T05:06:45.215272825",
  "completed": true,
  "inProgress": false
}
```

---

## 📚 7. 사용자 코스 진행도 (UserCourseProgress) API

### 7.1 모든 코스 진행도 조회
```http
GET /api/user-course-progress
```

**응답 예시:**
```json
[
  {
    "id": 1,
    "userId": 1,
    "courseId": 1,
    "courseName": "맛집 탐방 코스",
    "status": "IN_PROGRESS",
    "currentStep": 2,
    "totalSteps": 5,
    "progressPercentage": 40.0,
    "startedAt": "2025-08-24T04:00:00",
    "completedAt": null
  }
]
```

### 7.2 사용자별 코스 진행도 조회
```http
GET /api/user-course-progress/user/{userId}
```

### 7.3 사용자별 상태별 코스 진행도 조회
```http
GET /api/user-course-progress/user/{userId}/status/{status}
```

### 7.4 특정 사용자의 특정 코스 진행도 조회
```http
GET /api/user-course-progress/user/{userId}/course/{courseId}
```

### 7.5 코스 시작 ⭐
```http
POST /api/user-course-progress/start?userId={userId}&courseId={courseId}
```

### 7.6 진행도 업데이트 ⭐
```http
PUT /api/user-course-progress/progress?userId={userId}&courseId={courseId}&currentStep={currentStep}
```

### 7.7 코스 완료 ⭐
```http
POST /api/user-course-progress/complete?userId={userId}&courseId={courseId}
```

---

## 🏪 8. 스팟-미션 연결 (SpotMission) API

### 8.1 스팟에 연결된 모든 미션 조회
```http
GET /api/spots/{spotId}/missions
```

**응답 예시:**
```json
[
  {
    "missionId": 1,
    "title": "생활의 달인 만두국 인증",
    "description": "개천식당의 대표 메뉴, 슴슴하고 담백한 이북식 만두국을 주문하고 인증샷을 찍어보세요.",
    "missionType": "VISIT",
    "rewardPoints": 100,
    "spotId": 1,
    "spotName": "개천식당"
  }
]
```

### 8.2 스팟에 연결된 VISIT 타입 미션만 조회
```http
GET /api/spots/{spotId}/missions/visit
```

### 8.3 특정 스팟의 특정 미션 연결 조회
```http
GET /api/spots/{spotId}/missions/{missionId}
```

### 8.4 스팟에 미션 연결
```http
POST /api/spots/{spotId}/missions/{missionId}
```

### 8.5 스팟에 VISIT 타입 미션 연결
```http
POST /api/spots/{spotId}/missions/{missionId}/visit
```

### 8.6 스팟-미션 연결 해제
```http
DELETE /api/spots/{spotId}/missions/{missionId}
```

### 8.7 스팟에 연결된 미션 개수 조회
```http
GET /api/spots/{spotId}/missions/count
```

### 8.8 스팟에 연결된 VISIT 타입 미션 개수 조회
```http
GET /api/spots/{spotId}/missions/visit/count
```

---

## 🖼️ 9. 이미지 (Image) API

### 9.1 모든 이미지 조회
```http
GET /api/images
```

**응답 예시:**
```json
[
  {
    "imageId": 1,
    "imageUrl": "https://sijang-bojang.s3.ap-southeast-2.amazonaws.com/.../감자바위골.jpeg"
  }
]
```

### 9.2 특정 이미지 조회
```http
GET /api/images/{imageId}
```

### 9.3 이미지 생성
```http
POST /api/images
Content-Type: application/json

{
  "imageUrl": "https://example.com/new-image.jpg"
}
```

### 9.4 이미지 수정
```http
PUT /api/images/{imageId}
Content-Type: application/json

{
  "imageUrl": "https://example.com/updated-image.jpg"
}
```

### 9.5 이미지 삭제
```http
DELETE /api/images/{imageId}
```

---

## 🤖 10. AI 코스 추천 API

### 10.1 AI 코스 추천
```http
POST /api/courses/recommend
Content-Type: application/json

{
  "keywords": ["맛집", "카페", "전통"],
  "preferences": "가족과 함께 방문할 수 있는 곳을 찾고 있어요",
  "maxDuration": 120
}
```

**응답 예시:**
```json
{
  "recommendedCourses": [
    {
      "courseId": 1,
      "courseName": "맛집 탐방 코스",
      "description": "대전 중앙시장의 맛집들을 둘러보는 코스",
      "reason": "키워드 '맛집'과 '전통'에 부합하는 코스입니다.",
      "estimatedDuration": 90,
      "spots": [
        {
          "spotId": 1,
          "spotName": "개천식당",
          "category": "맛집"
        }
      ]
    }
  ],
  "totalRecommendations": 1
}
```

---

## 📊 11. 상태 코드

### 성공 응답
- `200 OK`: 요청 성공
- `201 Created`: 리소스 생성 성공

### 클라이언트 에러
- `400 Bad Request`: 잘못된 요청
- `404 Not Found`: 리소스를 찾을 수 없음

### 서버 에러
- `500 Internal Server Error`: 서버 내부 오류

---

## 🔧 12. 에러 응답 형식

```json
{
  "timestamp": "2025-08-24T05:06:45.215+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/api/users/999"
}
```

---

## 📝 13. 주요 비즈니스 로직

### 13.1 미션 완료 시 자동 처리
- 미션 완료 시 해당 미션이 속한 코스들의 완료 상태 자동 체크
- 모든 미션이 완료되면 코스도 자동 완료 처리

### 13.2 스탬프 시스템
- 코스 완료 시 사용자의 `completeStamp` 자동 증가
- `completeStamp`는 완료한 코스의 개수를 나타냄

### 13.3 진행률 계산
- 코스 진행률 = (현재 단계 / 전체 단계) × 100
- 단계는 1부터 시작

---

## 🚀 14. 배포 정보

- **서버**: AWS EC2 (3.34.186.143:8080)
- **데이터베이스**: MySQL (Docker)
- **프레임워크**: Spring Boot 3.x
- **Java 버전**: 17

---

## 📞 15. 문의사항

API 사용 중 문제가 발생하면 백엔드 개발팀에 문의해주세요.

---

**마지막 업데이트**: 2025-08-24
