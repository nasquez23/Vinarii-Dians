FROM openjdk:11-jre-slim

WORKDIR /winery-service

# Copy the JAR file into the container
COPY target/winery-service.jar /app/winery-service.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8082

# Command to run the Spring Boot application
CMD ["java", "-jar", "winery-service.jar"]
