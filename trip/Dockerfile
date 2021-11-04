FROM openjdk:11

ADD target/trip-0.0.1-SNAPSHOT.jar trip-app.jar
COPY src/main/resources/libs/TripPricer.jar TripPricer.jar

ENTRYPOINT ["java","-jar","trip-app.jar","TripPricer.jar"]