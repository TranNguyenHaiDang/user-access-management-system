FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/auth-service-1.0-SNAPSHOT.jar auth-service.jar

# Expose the port the app runs on
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "auth-service.jar"]