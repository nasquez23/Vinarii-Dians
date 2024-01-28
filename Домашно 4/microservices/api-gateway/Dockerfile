FROM openjdk:11-jre-slim

WORKDIR /api-gateway

# Copy the JAR file into the container
COPY target/microservice2.jar /app/microservice2.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8082

# Command to run the Spring Boot application
CMD ["java", "-jar", "api-gateway.jar"]
