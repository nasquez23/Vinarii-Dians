FROM openjdk:11-jre-slim

WORKDIR /reviews-service

# Copy the JAR file into the container
COPY target/reviews-service.jar /app/reviews-service.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8082

# Command to run the Spring Boot application
CMD ["java", "-jar", "reviews-service.jar"]
