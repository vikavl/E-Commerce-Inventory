FROM eclipse-temurin:17-jre

# Create app directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/*.jar /app/cloud-computing-inventory.jar

# Expose the port your application runs on
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "/app/cloud-computing-inventory.jar"]