# Dockerfile
FROM gradle:7.6-jdk17 AS build
COPY . /app
WORKDIR /app
RUN gradle clean build -x test

FROM openjdk:17-slim
COPY --from=build /app/build/libs/tacoCloud-0.0.1-SNAPSHOT.jar taco.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "taco.jar"]
