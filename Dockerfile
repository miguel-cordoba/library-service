FROM openjdk:17-jdk-slim

WORKDIR /src

COPY build/libs/*.jar LibraryService.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "LibraryService.jar"]
