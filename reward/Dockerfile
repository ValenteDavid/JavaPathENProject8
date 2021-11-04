FROM openjdk:11

ADD target/reward-0.0.1-SNAPSHOT.jar reward-app.jar
COPY src/main/resources/libs/RewardCentral.jar RewardCentral.jar

ENTRYPOINT ["java","-jar","reward-app.jar","RewardCentral.jar"]