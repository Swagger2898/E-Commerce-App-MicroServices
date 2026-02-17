# =========================
# Build stage (Maven)
# =========================
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# =========================
# Runtime stage (JRE only)
# =========================
FROM eclipse-temurin:17-jre

# Define build arg and copy built JAR
ARG JAR_FILE=/workspace/target/*-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} /app/app.jar

# ---- Security Hardening ----
# 1. Create non-root user (Debian style)
RUN groupadd --system appgroup && useradd --system --no-create-home --gid appgroup appuser

# 2. Set ownership and permissions
RUN chown -R appuser:appgroup /app
USER appuser

# ---- Networking ----
EXPOSE 8888

# ---- Runtime config ----
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=k8s"]
