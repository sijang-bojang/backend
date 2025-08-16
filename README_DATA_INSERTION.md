# 데이터 삽입 가이드

## 데이터 삽입 순서

다음 순서대로 SQL 파일을 실행해주세요:

### 1. 기본 데이터 삽입
```sql
-- 1. Markets 데이터 삽입
-- markets_data.sql 실행

-- 2. Types 데이터 삽입 (코스 분류용)
-- types_data.sql 실행

-- 3. Courses 데이터 삽입
-- courses_data.sql 실행

-- 4. Course-Types 연결 데이터 삽입
-- course_types_data.sql 실행

-- 5. Spots 데이터 삽입
-- spots_data.sql 실행

-- 6. Course-Spots 연결 데이터 삽입
-- course_spots_data.sql 실행

-- 7. Missions 데이터 삽입 (VISIT 타입, NON_VISIT 타입)
-- missions_data.sql 실행
-- miision_NON_VISIT.sql 실행

-- 8. Spot-Missions 연결 데이터 삽입
-- spot_missions_data.sql 실행
```

## 코스 분류 구조

### Types (코스 분류)
- **type_id: 1** - "가족이랑 가기 좋은 코스"
- **type_id: 2** - "연인과 가기 좋은 코스"

### Missions (VISIT 타입 미션들)
- **mission_id: 1-27** - 각 스팟별 방문 미션 (총 27개)
- **미션 타입**: VISIT (방문 미션)
- **보상 포인트**: 50-200점 (미션 난이도에 따라 차등)

### Missions (NON_VISIT 타입 미션들)
-- **mission_id: 28-34** - 스팟 방문과 무관한 미션 (총 6개)
-- **미션 타입** : NON_VISIT
- **보상 타입** : 50점으로 통일

### Courses (각 시장별 코스)
- **대전 중앙 시장**
  - 코스 1: 중앙시장 맛집 탐방 코스 (가족 코스) - 5개 스팟
  - 코스 2: 중앙시장 문화 체험 코스 (연인 코스) - 4개 스팟

- **대전 문창 시장**
  - 코스 3: 문창시장 떡 맛집 코스 (가족 코스) - 7개 스팟
  - 코스 4: 문창시장 야구 관람 코스 (가족 코스) - 3개 스팟

- **대전 역전 시장**
  - 코스 5: 역전시장 로컬 맛집 코스 (가족 코스) - 4개 스팟
  - 코스 6: 소제동 히든 스팟 코스 (연인 코스) - 5개 스팟

## JPA 엔티티 구조

### Course 엔티티
- `getTypes()`: 코스에 속한 타입들 조회
- `hasType(String typeName)`: 특정 타입인지 확인
- `isFamilyCourse()`: 가족 코스인지 확인
- `isCoupleCourse()`: 연인 코스인지 확인

### Type 엔티티
- `getCourses()`: 타입에 속한 코스들 조회
- `isFamilyType()`: 가족 코스 타입인지 확인
- `isCoupleType()`: 연인 코스 타입인지 확인

## 사용 예시

```java
// 가족 코스만 조회
List<Course> familyCourses = courseRepository.findAll().stream()
    .filter(Course::isFamilyCourse)
    .toList();

// 연인 코스만 조회
List<Course> coupleCourses = courseRepository.findAll().stream()
    .filter(Course::isCoupleCourse)
    .toList();

// 특정 타입의 코스들 조회
Type familyType = typeRepository.findByName("가족이랑 가기 좋은 코스");
List<Course> familyCourses = familyType.getCourses();

// VISIT 타입 미션들 조회
List<Mission> visitMissions = missionRepository.findByMissionType("VISIT");

// 특정 스팟의 미션들 조회
List<Mission> spotMissions = spotRepository.findById(spotId)
    .map(spot -> spot.getSpotMissions().stream()
        .map(SpotMission::getMission)
        .toList())
    .orElse(List.of());
```
