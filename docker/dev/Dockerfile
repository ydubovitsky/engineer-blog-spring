# How to run me?
# docker build . -f ./docker/dev/Dockerfile -t app:0.1
# docker run -d --rm -p 80:8000 --name app app:0.1

# How to make request from cmd?

#FROM openjdk:11
FROM adoptopenjdk/openjdk11:x86_64-ubuntu-jre-11.0.18_10
LABEL MAINTAINER="ydubovitsky"

# Setting up work directory
WORKDIR /spring-app

ARG BUILD_FINAL_NAME="engineer-blog-spring"

# Copy the jar file into our app
# The path below we take from pom.xml configs when start maven package
COPY ./target/engineer-blog-spring .

# Exposing port 8080
EXPOSE 8080

# Starting the application
# TODO How to use varible inside CMD? Beacuse memorush-spring.jar = ${BUILD_FINAL_NAME}.jar
CMD ["java", "-jar", "engineer-blog-spring.jar"]
