# 1. 빌드(Build) 단계: JAR 파일을 만드는 역할
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-jammy AS build

# 작업 폴더 설정
WORKDIR /workspace/app

# Gradle 관련 파일들을 먼저 복사해서 불필요한 재빌드를 방지
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 소스코드 복사
COPY src src

# Gradle 빌드 실행 (테스트는 제외)
RUN ./gradlew build -x test

# 2. 패키징(Package) 단계: 실제 실행될 이미지를 만드는 역할
FROM --platform=linux/amd64 eclipse-temurin:17-jre-jammy

# 작업 폴더 설정
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일을 복사
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# 컨테이너가 시작될 때 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]