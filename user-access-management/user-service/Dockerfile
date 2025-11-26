#Chọn base image - image nền để build container.

#eclipse-temurin — bản OpenJDK miễn phí từ Eclipse Foundation
#21 — Java version 21
#jdk — có đầy đủ JDK (compiler + runtime). Nếu chỉ cần chạy app, dùng jre sẽ nhẹ hơn
#jammy — dựa trên Ubuntu 22.04 LTS
FROM eclipse-temurin:21-jdk-jammy

#Tạo và chuyển vào thư mục làm việc /app trong container.
WORKDIR /app

#Tên file trong container (đổi tên ngắn gọn hơn)
#File sẽ nằm ở /app/user-service.jar trong container.
COPY target/user-service-1.0-SNAPSHOT.jar user-service.jar

# Expose the port the app runs on
#Khi chạy container, bạn vẫn cần -p 8082:8082 để map port ra ngoài
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "user-service.jar"]