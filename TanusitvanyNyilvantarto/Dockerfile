# 1. Build stage
FROM maven:3.9.4-eclipse-temurin-17 as build
WORKDIR /app

# Másoljuk be a teljes projektet
COPY . .

# Fordítás
RUN mvn clean package -DskipTests

# 2. Runtime stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Csak a buildelt jar fájlt másoljuk át
COPY --from=build /app/target/*.jar app.jar

# Indítás
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
