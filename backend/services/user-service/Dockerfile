FROM amazoncorretto:11-alpine-jdk
COPY target/user-service.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]