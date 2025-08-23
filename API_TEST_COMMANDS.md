# Spot-Mission API 테스트 명령어

## 1. 스팟에 연결된 모든 미션 조회
```bash
curl -X GET "http://localhost:8080/api/spots/1/missions"
```

## 2. 스팟에 연결된 VISIT 타입 미션만 조회
```bash
curl -X GET "http://localhost:8080/api/spots/1/missions/visit"
```

## 3. 특정 스팟의 특정 미션 연결 조회
```bash
curl -X GET "http://localhost:8080/api/spots/1/missions/1"
```

## 4. 스팟에 미션 연결
```bash
curl -X POST "http://localhost:8080/api/spots/1/missions/1"
```

## 5. 스팟에 VISIT 타입 미션 연결 (타입 검증 포함)
```bash
curl -X POST "http://localhost:8080/api/spots/1/missions/1/visit"
```

## 6. 스팟-미션 연결 해제
```bash
curl -X DELETE "http://localhost:8080/api/spots/1/missions/1"
```

## 7. 스팟에 연결된 미션 개수 조회
```bash
curl -X GET "http://localhost:8080/api/spots/1/missions/count"
```

## 8. 스팟에 연결된 VISIT 타입 미션 개수 조회
```bash
curl -X GET "http://localhost:8080/api/spots/1/missions/visit/count"
```

## 9. 스팟 정보 조회 (미션 정보 포함)
```bash
curl -X GET "http://localhost:8080/api/spots/1"
```

## 10. 모든 스팟 조회 (미션 정보 포함)
```bash
curl -X GET "http://localhost:8080/api/spots"
```

## 예상 응답 예시

### 스팟에 연결된 VISIT 미션 조회 응답
```json
[
  {
    "id": 1,
    "spotId": 1,
    "spotName": "맛집 스팟",
    "missionId": 1,
    "missionTitle": "맛집 방문하기",
    "missionType": "VISIT",
    "rewardPoints": 100
  }
]
```

### 스팟 정보 조회 응답 (미션 정보 포함)
```json
{
  "spotId": 1,
  "marketId": 1,
  "marketName": "대전 중앙 시장",
  "name": "맛집 스팟",
  "category": "맛집",
  "description": "맛있는 음식을 파는 곳",
  "imageUrl": "https://example.com/image.jpg",
  "latitude": 36.3308,
  "longitude": 127.4325,
  "missionCount": 2,
  "visitMissionTitles": ["맛집 방문하기", "사진 찍기"],
  "courseNames": ["맛집 투어", "시장 탐방"]
}
```
