# Spot-Mission API 명세서

## 개요
스팟(Spot)과 미션(Mission)을 연결하는 API입니다. 특히 VISIT 타입 미션과의 연결을 지원합니다.

---

## 1. 스팟에 연결된 모든 미션 조회

### Request
```http
GET /api/spots/{spotId}/missions
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |

### Response
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
  },
  {
    "id": 2,
    "spotId": 1,
    "spotName": "맛집 스팟",
    "missionId": 2,
    "missionTitle": "사진 찍기",
    "missionType": "PHOTO",
    "rewardPoints": 50
  }
]
```

### Status Codes
- `200 OK`: 성공적으로 조회됨
- `404 Not Found`: 스팟이 존재하지 않음

---

## 2. 스팟에 연결된 VISIT 타입 미션만 조회

### Request
```http
GET /api/spots/{spotId}/missions/visit
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |

### Response
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

### Status Codes
- `200 OK`: 성공적으로 조회됨
- `404 Not Found`: 스팟이 존재하지 않음

---

## 3. 특정 스팟의 특정 미션 연결 조회

### Request
```http
GET /api/spots/{spotId}/missions/{missionId}
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |
| missionId | Long | Yes | 미션 ID |

### Response
```json
{
  "id": 1,
  "spotId": 1,
  "spotName": "맛집 스팟",
  "missionId": 1,
  "missionTitle": "맛집 방문하기",
  "missionType": "VISIT",
  "rewardPoints": 100
}
```

### Status Codes
- `200 OK`: 성공적으로 조회됨
- `404 Not Found`: 스팟-미션 연결이 존재하지 않음

---

## 4. 스팟에 미션 연결

### Request
```http
POST /api/spots/{spotId}/missions/{missionId}
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |
| missionId | Long | Yes | 미션 ID |

### Response
```json
{
  "id": 1,
  "spotId": 1,
  "spotName": "맛집 스팟",
  "missionId": 1,
  "missionTitle": "맛집 방문하기",
  "missionType": "VISIT",
  "rewardPoints": 100
}
```

### Status Codes
- `200 OK`: 성공적으로 연결됨 (이미 연결된 경우 기존 연결 반환)
- `400 Bad Request`: 스팟 또는 미션이 존재하지 않음

---

## 5. 스팟에 VISIT 타입 미션 연결 (타입 검증 포함)

### Request
```http
POST /api/spots/{spotId}/missions/{missionId}/visit
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |
| missionId | Long | Yes | VISIT 타입 미션 ID |

### Response
```json
{
  "id": 1,
  "spotId": 1,
  "spotName": "맛집 스팟",
  "missionId": 1,
  "missionTitle": "맛집 방문하기",
  "missionType": "VISIT",
  "rewardPoints": 100
}
```

### Status Codes
- `200 OK`: 성공적으로 연결됨
- `400 Bad Request`: 미션이 VISIT 타입이 아니거나 존재하지 않음

---

## 6. 스팟-미션 연결 해제

### Request
```http
DELETE /api/spots/{spotId}/missions/{missionId}
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |
| missionId | Long | Yes | 미션 ID |

### Response
- No Content

### Status Codes
- `200 OK`: 성공적으로 연결 해제됨
- `400 Bad Request`: 연결이 존재하지 않음

---

## 7. 스팟에 연결된 미션 개수 조회

### Request
```http
GET /api/spots/{spotId}/missions/count
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |

### Response
```json
2
```

### Status Codes
- `200 OK`: 성공적으로 조회됨

---

## 8. 스팟에 연결된 VISIT 타입 미션 개수 조회

### Request
```http
GET /api/spots/{spotId}/missions/visit/count
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |

### Response
```json
1
```

### Status Codes
- `200 OK`: 성공적으로 조회됨

---

## 9. 스팟 정보 조회 (미션 정보 포함)

### Request
```http
GET /api/spots/{spotId}
```

### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| spotId | Long | Yes | 스팟 ID |

### Response
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
  "visitMissionTitles": ["맛집 방문하기"],
  "courseNames": ["맛집 투어", "시장 탐방"]
}
```

### Status Codes
- `200 OK`: 성공적으로 조회됨
- `404 Not Found`: 스팟이 존재하지 않음

---

## 데이터 모델

### SpotMissionDto
```json
{
  "id": "Long",
  "spotId": "Long",
  "spotName": "String",
  "missionId": "Long",
  "missionTitle": "String",
  "missionType": "String (VISIT, PHOTO, REVIEW, PURCHASE)",
  "rewardPoints": "Integer"
}
```

### SpotDto (업데이트됨)
```json
{
  "spotId": "Long",
  "marketId": "Long",
  "marketName": "String",
  "name": "String",
  "category": "String",
  "description": "String",
  "imageUrl": "String",
  "latitude": "Double",
  "longitude": "Double",
  "missionCount": "Integer",
  "visitMissionTitles": ["String"],
  "courseNames": ["String"]
}
```

---

## 에러 응답

### 400 Bad Request
```json
{
  "error": "Mission is not VISIT type"
}
```

### 404 Not Found
```json
{
  "error": "Spot or Mission not found"
}
```

---

## 사용 예시

### 1. VISIT 미션 연결
```bash
curl -X POST "http://localhost:8080/api/spots/1/missions/1/visit"
```

### 2. 연결된 VISIT 미션 조회
```bash
curl -X GET "http://localhost:8080/api/spots/1/missions/visit"
```

### 3. 스팟 정보 조회 (미션 포함)
```bash
curl -X GET "http://localhost:8080/api/spots/1"
```

---

## 데이터베이스 스키마

기존 `spot_mission` 테이블을 사용합니다:

```sql
CREATE TABLE spot_mission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    spot_id BIGINT,
    mission_id BIGINT,
    FOREIGN KEY (spot_id) REFERENCES spots(spot_id),
    FOREIGN KEY (mission_id) REFERENCES missions(mission_id)
);
```

추가적인 DB 수정은 필요하지 않습니다.
