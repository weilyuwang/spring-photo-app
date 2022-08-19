FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/photoapp-albums-service-0.0.1-SNAPSHOT.jar photoapp-albums-service.jar
ENTRYPOINT ["java","-jar","photoapp-albums-service.jar"]