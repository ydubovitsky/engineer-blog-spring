# How to run me?
# docker build . -f ./docker/production/Dockerfile -t engineer-blog-spring:0.0.1
# docker run -d --rm -p 80:8000 --name memorush-spring engineer-blog-spring:0.0.1

# How to make request from cmd?

# AS <NAME> to name this stage as maven
FROM maven:3.6.0-jdk-13-alpine AS maven
LABEL MAINTAINER="ydubovitsky"

WORKDIR /app
COPY ./src ./src
COPY pom.xml .
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 11,
#FROM adoptopenjdk/openjdk11
FROM adoptopenjdk/openjdk11:x86_64-ubuntu-jre-11.0.18_10

WORKDIR /opt/app

# Copy the dining-room-spring.jar from the maven stage to the /opt/app directory of the current stage.
# TODO How i can set name of the final .jar?
COPY --from=maven ./app/target/engineer-blog-spring.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar", "engineer-blog-spring.jar"]