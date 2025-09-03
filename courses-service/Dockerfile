FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
VOLUME /tmp
COPY target/courses-service-0.0.1.jar courses-service.jar
ENTRYPOINT ["java","-jar","/courses-service.jar"]