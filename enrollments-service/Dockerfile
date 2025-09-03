FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
VOLUME /tmp
COPY target/enrollments-service-0.0.1.jar enrollments-service.jar
ENTRYPOINT ["java","-jar","/enrollments-service.jar"]