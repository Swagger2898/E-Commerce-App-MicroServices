# ---------- Build Stage ----------
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Create non-root user and group
RUN groupadd --system appgroup && \
    useradd --system --gid appgroup --create-home appuser

# Copy the built jar directly using Docker's native wildcard resolution
COPY --from=build /workspace/target/*.jar app.jar

# Ensure proper permissions for non-root execution
RUN chown -R appuser:appgroup /app
USER appuser

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=k8s"]