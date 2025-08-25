#!/bin/bash

echo "=== Spring Boot API 종합 테스트 ==="
echo ""

echo "1. GET API 테스트"
echo "=================="

echo "1-1. Users API"
curl -s -X GET "http://localhost:8080/api/users" | jq
echo ""

echo "1-2. Markets API"
curl -s -X GET "http://localhost:8080/api/markets" | jq
echo ""

echo "1-3. Courses API"
curl -s -X GET "http://localhost:8080/api/courses" | jq
echo ""

echo "1-4. Spots API"
curl -s -X GET "http://localhost:8080/api/spots" | jq
echo ""

echo "1-5. Missions API"
curl -s -X GET "http://localhost:8080/api/missions" | jq
echo ""

echo "1-6. Images API"
curl -s -X GET "http://localhost:8080/api/images" | jq
echo ""

echo "1-7. User Missions API"
curl -s -X GET "http://localhost:8080/api/user-missions" | jq
echo ""

echo "1-8. User Course Progress API"
curl -s -X GET "http://localhost:8080/api/user-course-progress" | jq
echo ""

echo "1-9. Spot Missions API (Spot 1)"
curl -s -X GET "http://localhost:8080/api/spots/1/missions" | jq
echo ""

echo "1-10. Spot Missions API (Spot 2)"
curl -s -X GET "http://localhost:8080/api/spots/2/missions" | jq
echo ""

echo "1-11. Visit Missions by Spot 1"
curl -s -X GET "http://localhost:8080/api/spots/1/missions/visit" | jq
echo ""

echo "1-12. Mission Count by Spot 1"
curl -s -X GET "http://localhost:8080/api/spots/1/missions/count" | jq
echo ""

echo "1-13. Individual Image (ID: 1)"
curl -s -X GET "http://localhost:8080/api/images/1" | jq
echo ""

echo "1-14. Individual User (ID: 1)"
curl -s -X GET "http://localhost:8080/api/users/1" | jq
echo ""

echo ""
echo "2. POST API 테스트"
echo "=================="

echo "2-1. Create New User"
curl -s -X POST "http://localhost:8080/api/users" \
  -H "Content-Type: application/json" \
  -d '{"username":"테스트유저","email":"test@test.com","password":"1234"}' | jq
echo ""

echo "2-2. Create New Mission"
curl -s -X POST "http://localhost:8080/api/missions" \
  -H "Content-Type: application/json" \
  -d '{"title":"테스트미션","description":"테스트미션입니다","rewardPoints":100,"missionType":"VISIT"}' | jq
echo ""

echo "2-3. Create New Spot"
curl -s -X POST "http://localhost:8080/api/spots" \
  -H "Content-Type: application/json" \
  -d '{"spotName":"테스트스팟","description":"테스트스팟입니다","address":"테스트주소"}' | jq
echo ""

echo "2-4. Create New Image"
curl -s -X POST "http://localhost:8080/api/images" \
  -H "Content-Type: application/json" \
  -d '{"imageUrl":"https://example.com/test-image.jpg"}' | jq
echo ""

echo "2-5. Connect Spot to Mission"
curl -s -X POST "http://localhost:8080/api/spots/1/missions" \
  -H "Content-Type: application/json" \
  -d '{"missionId":1}' | jq
echo ""

echo "2-6. Create User Mission"
curl -s -X POST "http://localhost:8080/api/user-missions" \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"missionId":1,"completed":false}' | jq
echo ""

echo "2-7. Create User Course Progress"
curl -s -X POST "http://localhost:8080/api/user-course-progress" \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"courseId":1,"completed":false}' | jq
echo ""

echo ""
echo "3. PUT API 테스트"
echo "=================="

echo "3-1. Update User"
curl -s -X PUT "http://localhost:8080/api/users/1" \
  -H "Content-Type: application/json" \
  -d '{"username":"수정된유저","email":"updated@test.com","password":"5678"}' | jq
echo ""

echo "3-2. Update Mission"
curl -s -X PUT "http://localhost:8080/api/missions/1" \
  -H "Content-Type: application/json" \
  -d '{"title":"수정된미션","description":"수정된미션입니다","rewardPoints":200,"missionType":"VISIT"}' | jq
echo ""

echo "3-3. Update Image"
curl -s -X PUT "http://localhost:8080/api/images/1" \
  -H "Content-Type: application/json" \
  -d '{"imageUrl":"https://example.com/updated-image.jpg"}' | jq
echo ""

echo ""
echo "4. DELETE API 테스트"
echo "==================="

echo "4-1. Delete User Mission"
curl -s -X DELETE "http://localhost:8080/api/user-missions/1" | jq
echo ""

echo "4-2. Delete User Course Progress"
curl -s -X DELETE "http://localhost:8080/api/user-course-progress/1" | jq
echo ""

echo "4-3. Disconnect Spot from Mission"
curl -s -X DELETE "http://localhost:8080/api/spots/1/missions/1" | jq
echo ""

echo "4-4. Delete Image"
curl -s -X DELETE "http://localhost:8080/api/images/30" | jq
echo ""

echo ""
echo "=== 테스트 완료 ==="

