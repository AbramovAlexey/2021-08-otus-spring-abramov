FROM openjdk:17-jdk
COPY /target/hw17-docker.jar /app/app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]