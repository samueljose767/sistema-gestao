# 1. Prepara o ambiente e compila o código (Build)
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# 2. Prepara o servidor final leve (Run)
FROM eclipse-temurin:17-jre
COPY --from=build /app/target/*.jar /app/app.jar

# Expõe a porta 8081 que configurámos no application.properties
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]