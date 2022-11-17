FROM openjdk:17-ea-jdk-slim
VOLUME /tmp
COPY /build/libs/weather-magagement-0.0.1-SNAPSHOT.jar weather-magagement-0.0.1.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java","-jar","weather-magagement-0.0.1.jar "]