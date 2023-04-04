FROM gradle:7.2.0-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle clean build

FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/bumble-bee-1.0.0.jar .

EXPOSE 8088

CMD ["java", "-jar", "bumble-bee-1.0.0.jar"]