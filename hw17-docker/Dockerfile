FROM maven:3.8.4-openjdk-17-slim
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ADD ./pom.xml $PROJECT_DIR
RUN mvn dependency:resolve
ADD ./src/ $PROJECT_DIR/src
RUN mvn install
FROM openjdk:17-jdk
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=0 $PROJECT_DIR/target/hw17-docker* $PROJECT_DIR/
EXPOSE 8090
CMD ["java", "-jar", "/opt/project/hw17-docker.jar"]