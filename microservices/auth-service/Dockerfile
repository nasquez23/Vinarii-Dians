FROM openjdk:11-jre-slim

WORKDIR /auth-service

# Copy the JAR file into the container
COPY target/auth-service.jar /app/auth-service.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8082

# Command to run the Spring Boot application
CMD ["java", "-jar", "auth-service.jar"]
