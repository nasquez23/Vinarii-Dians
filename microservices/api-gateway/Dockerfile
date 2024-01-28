FROM openjdk:11-jre-slim

WORKDIR /api-gateway

# Copy the JAR file into the container
COPY target/api-gateway.jar /app/api-gateway.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8082

# Command to run the Spring Boot application
CMD ["java", "-jar", "api-gateway.jar"]
