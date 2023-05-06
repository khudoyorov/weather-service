FROM openjdk:17
COPY ./target/weather-service-0.0.1-SNAPSHOT.jar /src/weather-service/
WORKDIR /src/weather-service/reactive-appliaction
CMD ["java", "-jar", "weather-service-0.0.1-SNAPSHOT.jar"]
