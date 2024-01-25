FROM openjdk:21-jdk
WORKDIR /app
COPY build/libs/*.jar /app/word-cloud-core-server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "word-cloud-core-server.jar"]