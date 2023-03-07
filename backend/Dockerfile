# Use the official maven/Java 11 image as the base image
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and src directories to the working directory
COPY pom.xml .
COPY src/ ./src/

# Build the application using Maven
RUN mvn clean package -DskipTests

# Create a new image using the official OpenJDK 11 image as the base image
FROM openjdk:17

# Set the working directory to /app
WORKDIR /app

# Copy the built JAR file from the previous build stage to the working directory
COPY --from=builder /app/target/*.jar ./app.jar

# Start the Spring Boot application
CMD ["java", "-jar", "app.jar"]
