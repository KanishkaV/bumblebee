FROM gradle:7.2.0-jdk17 AS build

WORKDIR /app
USER root
COPY . .
ENV GRADLE_OPTS="-Xmx512m"
RUN gradle clean build --no-build-cache -Xmx512m

FROM openjdk:17-jdk

WORKDIR /app

COPY --from=build build/libs/bumble-bee-1.0.0.jar .

EXPOSE 8088

CMD ["java", "-jar", "bumble-bee-1.0.0.jar"]