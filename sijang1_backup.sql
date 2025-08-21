-- MySQL dump 10.13  Distrib 8.4.6, for Linux (aarch64)
--
-- Host: localhost    Database: sijang1
-- ------------------------------------------------------
-- Server version	8.4.6

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course_spots`
--

DROP TABLE IF EXISTS `course_spots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_spots` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `step_number` int DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `spot_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi48bqucoxn1vopbrkw8dk8sky` (`course_id`),
  KEY `FK85x3j5ju0as73666vf6vlbmnh` (`spot_id`),
  CONSTRAINT `FK85x3j5ju0as73666vf6vlbmnh` FOREIGN KEY (`spot_id`) REFERENCES `spots` (`spot_id`),
  CONSTRAINT `FKi48bqucoxn1vopbrkw8dk8sky` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_spots`
--

LOCK TABLES `course_spots` WRITE;
/*!40000 ALTER TABLE `course_spots` DISABLE KEYS */;
INSERT INTO `course_spots` VALUES (1,1,1,1),(2,2,1,2),(3,3,1,3),(4,4,1,4),(5,5,1,7),(6,1,2,5),(7,2,2,6),(8,3,2,8),(9,4,2,9),(10,1,3,10),(11,2,3,11),(12,3,3,12),(13,4,3,13),(14,5,3,14),(15,6,3,15),(16,7,3,16),(17,1,4,10),(18,2,4,17),(19,3,4,18),(20,1,5,19),(21,2,5,20),(22,3,5,21),(23,4,5,22),(24,1,6,23),(25,2,6,24),(26,3,6,25),(27,4,6,26),(28,5,6,27);
/*!40000 ALTER TABLE `course_spots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_types`
--

DROP TABLE IF EXISTS `course_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_types` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98rdjpk0rc0rbryqih2v7rvcf` (`course_id`),
  KEY `FKjpb7djmx9ml5r6wao8riktpqy` (`type_id`),
  CONSTRAINT `FK98rdjpk0rc0rbryqih2v7rvcf` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `FKjpb7djmx9ml5r6wao8riktpqy` FOREIGN KEY (`type_id`) REFERENCES `types` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_types`
--

LOCK TABLES `course_types` WRITE;
/*!40000 ALTER TABLE `course_types` DISABLE KEYS */;
INSERT INTO `course_types` VALUES (19,1,1),(20,3,1),(21,4,1),(22,5,1),(23,2,2),(24,6,2);
/*!40000 ALTER TABLE `course_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `market_id` bigint DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `FKjpnxn0b38ihmcvwuxskomisle` (`market_id`),
  CONSTRAINT `FKjpnxn0b38ihmcvwuxskomisle` FOREIGN KEY (`market_id`) REFERENCES `markets` (`market_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'대전 중앙시장의 대표 맛집들을 둘러보는 코스입니다. 전통 시장의 정취를 느끼며 다양한 음식을 맛볼 수 있습니다.','중앙시장 맛집 탐방 코스',1),(2,'중앙시장의 문화 공간과 쇼핑몰을 둘러보는 코스입니다. 전통과 현대가 공존하는 시장의 매력을 경험할 수 있습니다.','중앙시장 문화 체험 코스',1),(3,'대전의 떡 성지 문창시장에서 다양한 떡을 맛보는 코스입니다. 50년 전통의 떡집들을 둘러볼 수 있습니다.','문창시장 떡 맛집 코스',2),(4,'문창시장 맛집 탐방 후 한화 이글스파크에서 야구 경기를 관람하는 코스입니다.','문창시장 야구 관람 코스',2),(5,'대전역 근처 역전시장의 현지인 맛집들을 둘러보는 코스입니다.','역전시장 로컬 맛집 코스',3),(6,'역전시장에서 시작하여 소제동의 숨겨진 카페와 관광지를 둘러보는 코스입니다.','소제동 히든 스팟 코스',3);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `image_id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `markets`
--

DROP TABLE IF EXISTS `markets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `markets` (
  `market_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`market_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `markets`
--

LOCK TABLES `markets` WRITE;
/*!40000 ALTER TABLE `markets` DISABLE KEYS */;
INSERT INTO `markets` VALUES (1,'대전광역시 동구 대전로 783','중구에 위치하는 시장',36.3308,127.4325,'대전 중앙 시장'),(2,'대전광역시 중구 문창로 10번길 38','문창동에 위치하는 시장',36.3175,127.4292,'대전 문창 시장'),(3,'대전광역시 동구 역전시장길 34','대전역 주변에 위치하는 시장',36.3300438245,127.4338531035,'대전 역전 시장');
/*!40000 ALTER TABLE `markets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `missions`
--

DROP TABLE IF EXISTS `missions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `missions` (
  `mission_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `mission_type` varchar(255) DEFAULT NULL,
  `reward_points` int DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`mission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `missions`
--

LOCK TABLES `missions` WRITE;
/*!40000 ALTER TABLE `missions` DISABLE KEYS */;
INSERT INTO `missions` VALUES (1,'개천식당의 대표 메뉴, 슴슴하고 담백한 이북식 만두국을 주문하고 인증샷을 찍어보세요.','VISIT',100,'생활의 달인 만두국 인증'),(2,'스모프치킨의 중독성 강한 매콤달콤 특제 간장 소스 치킨을 사진으로 남겨주세요.','VISIT',100,'단짠단짠 특제 간장치킨'),(3,'서울치킨에서만 맛볼 수 있는 특별 메뉴, 닭내장탕을 주문하고 인증샷을 찍어보세요.','VISIT',120,'노포의 특별 메뉴, 닭내장탕'),(4,'싸지롱의 고즈넉한 분위기가 느껴지는 공간 혹은 핸드드립 커피 사진을 찍어 공유해주세요.','VISIT',100,'시장 속 비밀 카페 탐방'),(5,'성심당문화원에서 판매하는 특별한 굿즈나 전시물을 배경으로 사진을 찍어보세요.','VISIT',100,'성심당 문화 탐방기'),(6,'미들커피의 현대적인 인테리어나 맛있는 커피를 감성적인 사진으로 남겨주세요.','VISIT',100,'전통과 현대의 조화'),(7,'청년구단의 대형 스크린 앞에서 스포츠 경기를 관람하거나 음식을 즐기는 모습을 인증해주세요.','VISIT',110,'청년구단에서 응원하기'),(8,'동구夜 놀자 야시장의 활기찬 분위기 속에서 맛있는 길거리 음식을 즐기는 모습을 사진으로 남겨주세요.','VISIT',110,'야시장의 밤을 즐겨라!'),(9,'중앙쇼핑타워에서 판매하는 그릇, 침구류 등 마음에 드는 혼수용품 사진을 찍어보세요.','VISIT',100,'혼수 장만 리스트업'),(10,'감자바위골의 명물, 얼굴만 한 크기의 대왕 감자전을 맛있게 먹는 모습을 사진으로 인증하세요.','VISIT',120,'대왕 감자전 맛보기'),(11,'진주냉면 이설옥의 특별한 해물 육수 냉면을 사진으로 찍어 맛을 기록해보세요.','VISIT',100,'진주냉면의 깊은 맛'),(12,'광천순대의 진한 국물이 일품인 순대국밥으로 든든한 한 끼를 해결하고 인증샷을 남겨주세요.','VISIT',100,'든든한 순대국밥 한 그릇'),(13,'베베꽈배기에서 가장 독특하고 맛있어 보이는 수제 꼬임도넛을 골라 사진을 찍어보세요.','VISIT',100,'오늘의 디저트는 꽈배기'),(14,'카페 그림의 아늑하고 독특한 인테리어를 배경으로 멋진 사진을 남겨주세요.','VISIT',100,'그림같은 카페에서의 휴식'),(15,'영순카페의 오랜 역사가 느껴지는 공간에서 커피를 즐기는 여유로운 순간을 포착해보세요.','VISIT',100,'시장의 정취, 전통 카페'),(16,'문창시장 떡집 거리에서 가장 맛있어 보이는 떡을 구매하고 인증샷을 찍어주세요.','VISIT',110,'떡 성지 순례 인증'),(17,'한화생명 이글스파크를 배경으로 열정적인 응원의 순간이나 경기장 전경을 사진에 담아보세요.','VISIT',120,'이글스파크 직관 인증!'),(18,'보문산 행복 숲 둘레길을 걸으며 아름다운 대전의 전경을 배경으로 멋진 사진을 찍어보세요.','VISIT',110,'보문산에서 대전 조망하기'),(19,'김화칼국수의 명물, 고소한 들깨가루가 듬뿍 들어간 칼국수를 맛있게 먹기 전 사진을 찍어주세요.','VISIT',100,'고소한 들깨 칼국수'),(20,'호돌이만두의 가성비 넘치는 맛있는 만두를 간식으로 즐기고 인증샷을 남겨보세요.','VISIT',100,'추억의 동네 만두'),(21,'별난집의 대표 메뉴, 매콤하고 중독성 있는 두부두루치기를 사진으로 인증해주세요.','VISIT',120,'대전의 맛, 두부두루치기'),(22,'챔프스페이스커피로스터스의 스페셜티 커피와 도넛을 감각적인 사진으로 남겨보세요.','VISIT',100,'소제동 핫플 커피'),(23,'카페 흐그이므자의 귀여운 디저트와 깔끔한 인테리어가 함께 나오도록 사진을 찍어주세요.','VISIT',100,'흑임자 카페의 디저트'),(24,'화양연화의 멋진 한옥 테라스에 앉아 여유를 즐기는 모습을 사진으로 담아보세요.','VISIT',100,'한옥 카페의 여유'),(25,'소제동 철도관사촌의 고즈넉한 골목길을 거닐며 가장 마음에 드는 장소에서 사진을 찍어 공유해주세요.','VISIT',110,'과거와 현재의 공존, 소제동 탐방'),(26,'꿈돌이 하우스에 방문하여 귀여운 꿈돌이 굿즈나 조형물과 함께 기념사진을 찍어보세요.','VISIT',100,'꿈돌이와 함께 찰칵!'),(27,'대전트래블라운지에 방문하여 여행 정보를 얻거나 쉬어가는 모습을 사진으로 인증해주세요.','VISIT',100,'대전 여행의 시작점'),(28,'앱에 접속하여 오늘의 출석 도장을 찍고 포인트를 받아가세요.','NON_VISIT',50,'매일매일 출석체크'),(29,'장소 방문 미션 중 아무거나 하나를 완료하고 첫 발걸음을 기념하세요.','NON_VISIT',50,'첫 미션 완료의 감동'),(30,'대전의 다양한 시장 5곳 이상을 방문하여 스탬프를 모아보세요.','NON_VISIT',50,'시장 5곳 탐방 완료'),(31,'추천 코스 중 하나를 선택하여 모든 장소를 방문하고 코스를 완주하세요.','NON_VISIT',50,'첫 번째 코스 완주'),(32,'총 3개의 추천 코스를 완주하여 대전 시장 마스터에 한 걸음 다가가세요.','NON_VISIT',50,'코스 3개 정복'),(33,'종류에 상관없이 10개의 미션을 성공적으로 완료하고 성취감을 느껴보세요.','NON_VISIT',50,'미션 10개 달성'),(34,'다양한 미션을 통해 포인트를 모아 총 1000포인트를 달성해보세요.','NON_VISIT',50,'누적 1000포인트 달성');
/*!40000 ALTER TABLE `missions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spot_mission`
--

DROP TABLE IF EXISTS `spot_mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spot_mission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mission_id` bigint DEFAULT NULL,
  `spot_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmk1e6brfmdtkhce3hew4gcl52` (`mission_id`),
  KEY `FKo08xbxjg00ci5pte1e5xwdx3d` (`spot_id`),
  CONSTRAINT `FKmk1e6brfmdtkhce3hew4gcl52` FOREIGN KEY (`mission_id`) REFERENCES `missions` (`mission_id`),
  CONSTRAINT `FKo08xbxjg00ci5pte1e5xwdx3d` FOREIGN KEY (`spot_id`) REFERENCES `spots` (`spot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spot_mission`
--

LOCK TABLES `spot_mission` WRITE;
/*!40000 ALTER TABLE `spot_mission` DISABLE KEYS */;
INSERT INTO `spot_mission` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7),(8,8,8),(9,9,9),(10,10,10),(11,11,11),(12,12,12),(13,13,13),(14,14,14),(15,15,15),(16,16,16),(17,17,17),(18,18,18),(19,19,19),(20,20,20),(21,21,21),(22,22,22),(23,23,23),(24,24,24),(25,25,25),(26,26,26),(27,27,27);
/*!40000 ALTER TABLE `spot_mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spots`
--

DROP TABLE IF EXISTS `spots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spots` (
  `spot_id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `image_id` bigint DEFAULT NULL,
  `market_id` bigint DEFAULT NULL,
  PRIMARY KEY (`spot_id`),
  KEY `FKhuvh19ro90wgsipt8ol60s3vo` (`image_id`),
  KEY `FKgsveo67lp0ej95x4get1hqlw9` (`market_id`),
  CONSTRAINT `FKgsveo67lp0ej95x4get1hqlw9` FOREIGN KEY (`market_id`) REFERENCES `markets` (`market_id`),
  CONSTRAINT `FKhuvh19ro90wgsipt8ol60s3vo` FOREIGN KEY (`image_id`) REFERENCES `images` (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spots`
--

LOCK TABLES `spots` WRITE;
/*!40000 ALTER TABLE `spots` DISABLE KEYS */;
INSERT INTO `spots` VALUES (1,'식당','자극적이지 않고 담백한 맛이 일품인 이북식 손만두 전문점입니다. \'생활의 달인\'에도 소개된 숨은 맛집으로, 슴슴한 만두국이 별미입니다.',36.3294,127.4303,'개천식당',NULL,1),(2,'식당','매콤달콤한 특제 간장 소스로 유명한, 대전 현지인들이 사랑하는 치킨 맛집입니다. 바삭한 튀김과 어우러지는 중독성 있는 맛을 경험해 보세요.',36.3308,127.4325,'스모프치킨',NULL,1),(3,'식당','옛날 통닭의 맛과 정취를 그대로 간직한 노포입니다. 바삭한 후라이드 치킨과 함께, 이곳에서만 맛볼 수 있는 특별 메뉴 \'닭내장탕\'도 인기가 많습니다.',36.3297,127.4318,'서울치킨',NULL,1),(4,'카페','시장 골목에 비밀스럽게 숨어있는 핸드드립 커피 전문점. 독특하고 고즈넉한 분위기 속에서 수준 높은 커피를 즐길 수 있는 특별한 공간입니다.',36.3296,127.4303,'싸지롱',NULL,1),(5,'카페','대전의 명물 성심당이 운영하는 복합문화공간. 성심당 빵과 함께 음료를 즐기고, 다양한 굿즈와 전시를 구경할 수 있는 관광 필수 코스입니다.',36.3276,127.4281,'성심당문화원',NULL,1),(6,'카페','전통시장 골목과 현대적인 인테리어의 조화가 돋보이는 감성 카페입니다. SNS 핫플레이스',36.329,127.4295,'미들커피',NULL,1),(7,'즐길거리','청년 셰프들이 운영하는 개성 넘치는 푸드코트. 다양한 음식과 함께 대형 스크린으로 스포츠 경기를 관람할 수 있는 시장 속 젊음의 공간입니다.',36.3308,127.4325,'청년구단',NULL,1),(8,'즐길거리','매주 금, 토요일 저녁에 열리는 활기찬 야시장. 맛있는 길거리 음식과 다채로운 공연이 어우러져 시장의 밤을 축제처럼 만들어 줍니다.',36.33,127.433,'동구夜 놀자 야시장',NULL,1),(9,'즐길거리','시장 내에 위치한 현대식 쇼핑몰. 혼수, 그릇, 침구류 등을 전문적으로 판매하며, 쾌적한 환경에서 쇼핑을 즐길 수 있습니다.',36.3308,127.4325,'중앙쇼핑타워',NULL,1),(10,'식당','얼굴만 한 크기의 \'대왕 감자전\'으로 전국적인 명성을 얻은 곳. 겉은 바삭, 속은 촉촉한 감자전과 푸짐한 칼국수는 문창시장 방문의 이유가 됩니다.',36.3181,127.4293,'감자바위골',NULL,2),(11,'식당','해물 육수의 깊은 감칠맛이 특징인 \'진주냉면\' 전문점. 평범한 냉면이 아닌 특별한 맛을 찾는 미식가들에게 추천하는 곳입니다.',36.3188,127.4278,'진주냉면 이설옥',NULL,2),(12,'식당','진한 국물과 푸짐한 건더기를 자랑하는 순대국밥 맛집. 저렴한 가격으로 든든한 한 끼를 해결할 수 있어 현지인들에게 인기가 높습니다.',36.3172,127.4281,'문창동 광천순대',NULL,2),(13,'카페','독특한 맛의 수제 꼬임도넛으로 유명한 디저트 카페. 다양한 맛의 도넛과 함께 즐기는 커피가 일품입니다.',36.3178,127.4287,'베베꽈 배기',NULL,2),(14,'카페','문창시장 근처에 위치한 아늑한 분위기의 카페. 전통과 현대가 조화를 이루는 독특한 인테리어가 특징입니다.',36.3194,127.4291,'카페 그림',NULL,2),(15,'카페','오랜 역사를 가진 전통 카페. 시장의 정취를 느낄 수 있는 분위기와 함께 즐기는 커피가 특별합니다.',36.3184,127.4279,'영순카페',NULL,2),(16,'즐길거리','50년 전통의 떡 전문점들이 모여있는 거리. 다양한 종류의 떡과 전통 음식을 맛볼 수 있는 대전의 떡 성지입니다.',36.3175,127.4292,'문창시장 떡집 거리',NULL,2),(17,'즐길거리','한화 이글스의 홈구장. 야구 경기 관람뿐만 아니라 구장 내 다양한 시설을 즐길 수 있는 복합 스포츠 공간입니다.',36.3172,127.4292,'한화생명 이글스파크',NULL,2),(18,'즐길거리','도시 속 자연을 만날 수 있는 둘레길. 산책과 등산을 즐기며 도시의 아름다운 전경을 감상할 수 있습니다.',36.3119,127.4221,'보문산 행복 숲 둘레길',NULL,2),(19,'식당','들깨가루가 듬뿍 들어가 고소한 국물이 일품인 칼국수집. 대전역과 가까워 여행객들이 대전의 맛을 제대로 느낄 수 있는 곳으로 특히 인기가 많습니다.',36.3315,127.4348,'김화칼국수',NULL,3),(20,'식당','저렴한 가격과 변함없는 맛으로 오랫동안 사랑받아온 동네 만두 맛집. 출출할 때 부담 없이 즐기기 좋은 최고의 간식입니다.',36.333,127.4363,'호돌이만두',NULL,3),(21,'식당','매콤한 양념의 \'두부두루치기\'가 대표 메뉴인 대전 현지인 맛집. 중독성 있는 맛으로 대전의 독특한 향토 음식을 경험하고 싶다면 꼭 방문해야 할 곳입니다.',36.3293,127.4343,'별난집',NULL,3),(22,'카페','소제동의 핫플레이스. 직접 로스팅한 스페셜티 커피와 맛있는 도넛을 함께 즐길 수 있는 감각적인 공간입니다.',36.3343,127.4371,'챔프스페이스커피로스터스',NULL,3),(23,'카페','\'흑임자\'라는 독특한 이름의 모던한 감성 카페. 귀여운 디저트와 깔끔한 인테리어로 인기가 많습니다.',36.324,127.4262,'흐그이므자',NULL,3),(24,'카페','한옥의 멋을 느낄 수 있는 분위기 좋은 카페. 아늑한 테라스에 앉아 여유로운 시간을 보내기 좋습니다.',36.334,127.4377,'화양연화',NULL,3),(25,'즐길거리','오래된 철도 노동자들의 관사가 보존되어 있는 역사적인 마을. 과거와 현재가 공존하는 골목을 거닐며 숨겨진 카페와 맛집을 발견하는 재미가 있습니다.',36.3356,127.4337,'소제동 철도관사촌',NULL,3),(26,'즐길거리','대전의 마스코트 \'꿈돌이\'를 테마로 한 관광안내소 겸 굿즈샵. 여행 정보를 얻고 귀여운 기념품도 구매할 수 있는 재미있는 공간입니다.',36.3311,127.434,'꿈돌이 하우스',NULL,3),(27,'즐길거리','여행자들을 위한 공식적인 쉼터이자 안내소. 짐 보관, 여행 상담 등 실질적인 편의를 제공하여 대전 여행의 훌륭한 시작점이 되어 줍니다.',36.3296,127.4328,'대전트래블라운지',NULL,3);
/*!40000 ALTER TABLE `spots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `types` (
  `type_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES (1,'가족'),(2,'연인');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_course_progress`
--

DROP TABLE IF EXISTS `user_course_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_course_progress` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `completed_at` datetime(6) DEFAULT NULL,
  `current_step` int DEFAULT NULL,
  `started_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjj3sw5b9g86gydd6tdvnuailh` (`course_id`),
  KEY `FKj5thv4d609cmoa8mxffdfluy1` (`user_id`),
  CONSTRAINT `FKj5thv4d609cmoa8mxffdfluy1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKjj3sw5b9g86gydd6tdvnuailh` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_course_progress`
--

LOCK TABLES `user_course_progress` WRITE;
/*!40000 ALTER TABLE `user_course_progress` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_course_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_mission`
--

DROP TABLE IF EXISTS `user_mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_mission` (
  `user_mission_id` bigint NOT NULL AUTO_INCREMENT,
  `completed_at` datetime(6) DEFAULT NULL,
  `started_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `mission_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_mission_id`),
  KEY `FK3g522x99ajvlr1f06y8aj9446` (`mission_id`),
  KEY `FK5sl9s3v4lwwwwxmphfdmlvv8` (`user_id`),
  CONSTRAINT `FK3g522x99ajvlr1f06y8aj9446` FOREIGN KEY (`mission_id`) REFERENCES `missions` (`mission_id`),
  CONSTRAINT `FK5sl9s3v4lwwwwxmphfdmlvv8` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_mission`
--

LOCK TABLES `user_mission` WRITE;
/*!40000 ALTER TABLE `user_mission` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `exp` int DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `total_reward` int DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'xodms3320@o.cnu.ac.kr',0,'1234',0,'엄태은');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-20 21:02:24
