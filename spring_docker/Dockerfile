FROM openjdk:18-jdk-alpine as build
ENV PROJECT_DIR=/opt/project
WORKDIR $PROJECT_DIR
ADD ./pom.xml $PROJECT_DIR
RUN mvn dependency:resolve
ADD ./src/ $PROJECT_DIR/src
RUN mvn clean install

FROM openjdk:18-jdk-alpine
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=build $PROJECT_DIR/target/spring-docker.jar $PROJECT_DIR/

EXPOSE 8080

CMD ["java", "-jar", "/opt/project/spring-docker.jar"]