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

# Set working directory explicitly
WORKDIR /app

# Create non-root user
RUN groupadd --system appgroup && useradd --system --no-create-home --gid appgroup appuser

# Copy the built JAR from target directory directly into /app/app.jar
COPY --from=build /workspace/target/*.jar /app/app.jar

# Set ownership
RUN chown -R appuser:appgroup /app
USER appuser

# ---- Networking ----
EXPOSE 8888

# ---- Runtime config ----
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=k8s"]








## =========================
## Build stage (Maven)
## =========================
#FROM maven:3.9.5-eclipse-temurin-17 AS build
#WORKDIR /workspace
#COPY pom.xml .
#COPY src ./src
#RUN mvn -B -DskipTests clean package
#
## =========================
## Runtime stage (JRE only)
## =========================
#FROM eclipse-temurin:17-jre
#
## Define build arg and copy built JAR
#ARG JAR_FILE=/workspace/target/*-SNAPSHOT.jar
#COPY --from=build ${JAR_FILE} /app/app.jar
#
## ---- Security Hardening ----
## 1. Create non-root user (Debian style)
#RUN groupadd --system appgroup && useradd --system --no-create-home --gid appgroup appuser
#
## 2. Set ownership and permissions
#RUN chown -R appuser:appgroup /app
#USER appuser
#
## ---- Networking ----
#EXPOSE 8888
#
## ---- Runtime config ----
#ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=k8s"]
