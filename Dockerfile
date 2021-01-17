FROM openjdk:8-jdk-alpine

COPY target/springboot-api.jar springboot-api.jar
ENTRYPOINT ["java","-jar","/springboot-api.jar"]