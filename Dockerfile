# Use OpenJDK as the base image
FROM openjdk:23-jdk-slim

# Set working directory
WORKDIR /app

# Copy the application JAR file
COPY target/HR-Sunflowers-*.jar app.jar

# Expose the port
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
