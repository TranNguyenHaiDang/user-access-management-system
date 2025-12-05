FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/user-access-management-0.0.1-SNAPSHOT.jar user-access-management.jar

# Expose the port the app runs on
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "user-access-management.jar"]