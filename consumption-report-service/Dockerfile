# Use the official OpenJDK 11 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built by Gradle into the container
COPY build/libs/app.jar /app/app.jar

# Expose the port your Spring Boot application is running on
EXPOSE 8080

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]