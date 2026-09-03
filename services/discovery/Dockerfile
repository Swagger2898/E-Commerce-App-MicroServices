# ---------- Build Stage ----------
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package


# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar from build stage
COPY --from=build /workspace/target/*.jar app.jar

# Create non-root user and group (Debian syntax)
RUN groupadd --system appgroup && \
    useradd --system --gid appgroup --create-home appuser

# Use non-root user
USER appuser

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=k8s"]
