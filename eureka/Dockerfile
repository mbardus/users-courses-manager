FROM eclipse-temurin:17-jre-focal
RUN apt-get update && apt-get install -y curl
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]