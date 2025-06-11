# ========================================
# Stage 1: Build Stage
# Uses Maven to build the Spring Boot application
# ========================================
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy only the POM file first
# This is done separately to leverage Docker's layer caching
# If pom.xml doesn't change, Maven won't download dependencies again
COPY pom.xml .

# Download all required dependencies
# This is done in a separate step to cache the dependencies
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
# -DskipTests flag skips tests to speed up the build process
RUN mvn package -DskipTests

# ========================================
# Stage 2: Production Stage
# Creates a minimal JRE image with just the built JAR
# ========================================
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR from the build stage
# The wildcard *.jar is used because the JAR name includes version numbers
COPY --from=build /app/target/*.jar app.jar

# Document that the container listens on port 8080
EXPOSE 8080

# Run the Spring Boot application
# The -jar flag tells Java to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"] 