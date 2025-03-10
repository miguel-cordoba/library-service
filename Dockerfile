FROM openjdk:17-jdk-slim

WORKDIR /src

COPY build/libs/*.jar library-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "library-service.jar"]
