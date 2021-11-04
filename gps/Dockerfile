FROM openjdk:11

ADD target/gps-0.0.1-SNAPSHOT.jar gps-app.jar
COPY src/main/resources/libs/gpsUtil.jar gpsUtil.jar

ENTRYPOINT ["java","-jar","gps-app.jar","gpsUtil.jar"]