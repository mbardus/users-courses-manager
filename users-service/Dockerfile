FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
VOLUME /tmp
COPY target/users-service-0.0.1.jar users-service.jar
ENTRYPOINT ["java","-jar","/users-service.jar"]