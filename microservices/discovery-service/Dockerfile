FROM openjdk:11-jre-slim

WORKDIR /discovery-service

# Copy the JAR file into the container
COPY target/discovery-service.jar /app/discovery-service.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8082

# Command to run the Spring Boot application
CMD ["java", "-jar", "discovery-service.jar"]
