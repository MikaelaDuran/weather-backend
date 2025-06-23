# === Base Stage ===
# Installs dependencies for both dev and prod.
FROM maven:3.8.5-openjdk-17 AS base
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# === Development Stage ===
# Runs the app with Maven to enable hot-reloading.
FROM base AS development
COPY src ./src
# Expose app and debug ports
EXPOSE 8080
EXPOSE 5005
# This command enables hot-reloading via spring-boot-devtools
CMD ["mvn", "spring-boot:run"]

# === Production Stage ===
# Builds the final JAR and runs it in a slim JRE.
FROM base AS builder
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim AS production
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"] 