# 1단계: Maven + JDK 17 포함 이미지로 빌드
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

# 2단계: 실행 전용 (JRE만 포함)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8180
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
