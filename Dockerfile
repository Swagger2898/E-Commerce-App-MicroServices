# ---------- Build Stage ----------
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

ARG JAR_FILE=/workspace/target/*-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} app.jar

RUN groupadd --system appgroup && useradd --system --gid appgroup --create-home appuser
USER appuser

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=k8s"]
