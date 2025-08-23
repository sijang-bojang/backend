# Spot-Mission API 명세서 (최종)

## 개요
스팟(Spot)과 미션(Mission)을 연결하는 API입니다. 특히 VISIT 타입 미션과의 연결을 지원합니다.

**기본 URL**: `http://3.34.186.143:8080`

---

## 1. 스팟 정보 조회 (미션 정보 포함)

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
  "name": "개천식당",
  "category": "식당",
  "description": "자극적이지 않고 담백한 맛이 일품인 이북식 손만두 전문점입니다. '생활의 달인'에도 소개된 숨은 맛집으로, 슴슴한 만두국이 별미입니다.",
  "imageUrl": null,
  "latitude": 36.3287,
  "longitude": 127.4316,
  "missionCount": 1,
  "visitMissionTitles": ["생활의 달인 만두국 인증"],
  "courseNames": ["중앙시장 맛집 탐방 코스"]
}
```

### Status Codes
- `200 OK`: 성공적으로 조회됨
- `404 Not Found`: 스팟이 존재하지 않음

---

## 2. 스팟에 연결된 모든 미션 조회

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
    "spotName": "개천식당",
    "missionId": 1,
    "missionTitle": "생활의 달인 만두국 인증",
    "missionType": "VISIT",
    "rewardPoints": 100
  }
]
```

### Status Codes
- `200 OK`: 성공적으로 조회됨
- `404 Not Found`: 스팟이 존재하지 않음

---

## 3. 스팟에 연결된 VISIT 타입 미션만 조회

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
    "spotName": "개천식당",
    "missionId": 1,
    "missionTitle": "생활의 달인 만두국 인증",
    "missionType": "VISIT",
    "rewardPoints": 100
  }
]
```

### Status Codes
- `200 OK`: 성공적으로 조회됨
- `404 Not Found`: 스팟이 존재하지 않음

---

## 4. 특정 스팟의 특정 미션 연결 조회

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
  "spotName": "개천식당",
  "missionId": 1,
  "missionTitle": "생활의 달인 만두국 인증",
  "missionType": "VISIT",
  "rewardPoints": 100
}
```

### Status Codes
- `200 OK`: 성공적으로 조회됨
- `404 Not Found`: 스팟-미션 연결이 존재하지 않음

---

## 5. 스팟에 미션 연결

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
  "spotName": "개천식당",
  "missionId": 1,
  "missionTitle": "생활의 달인 만두국 인증",
  "missionType": "VISIT",
  "rewardPoints": 100
}
```

### Status Codes
- `200 OK`: 성공적으로 연결됨 (이미 연결된 경우 기존 연결 반환)
- `400 Bad Request`: 스팟 또는 미션이 존재하지 않음

---

## 6. 스팟에 VISIT 타입 미션 연결 (타입 검증 포함)

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
  "spotName": "개천식당",
  "missionId": 1,
  "missionTitle": "생활의 달인 만두국 인증",
  "missionType": "VISIT",
  "rewardPoints": 100
}
```

### Status Codes
- `200 OK`: 성공적으로 연결됨
- `400 Bad Request`: 미션이 VISIT 타입이 아니거나 존재하지 않음

---

## 7. 스팟-미션 연결 해제

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

## 8. 스팟에 연결된 미션 개수 조회

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
1
```

### Status Codes
- `200 OK`: 성공적으로 조회됨

---

## 9. 스팟에 연결된 VISIT 타입 미션 개수 조회

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

## 데이터 모델

### SpotDto
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

### 1. 스팟 정보 조회
```bash
curl -X GET "http://3.34.186.143:8080/api/spots/1"
```

### 2. VISIT 미션 조회
```bash
curl -X GET "http://3.34.186.143:8080/api/spots/1/missions/visit"
```

### 3. VISIT 미션 연결
```bash
curl -X POST "http://3.34.186.143:8080/api/spots/1/missions/1/visit"
```

### 4. 미션 개수 조회
```bash
curl -X GET "http://3.34.186.143:8080/api/spots/1/missions/count"
```

---

## 포트 설정

**현재 설정**: `http://3.34.186.143:8080`
- Spring Boot 애플리케이션이 8080 포트에서 실행 중
- `--network host` 사용으로 포트 매핑 없이 직접 접근

**권장 설정**: `http://3.34.186.143:5000`
- 컨테이너를 `-p 5000:8080`으로 포트 매핑하여 5000 포트로 외부 접근

---

## 배포 정보

- **서버**: AWS EC2 (3.34.186.143)
- **포트**: 8080 (내부), 5000 (외부 권장)
- **데이터베이스**: MySQL (sijang1)
- **프로필**: prod
- **컨테이너**: Docker (sijang-backend)
