FROM maven:3.8.1-openjdk-17 AS build
LABEL authors="lucas"
WORKDIR /app
COPY pom.xml .
COPY src /app/src
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /usr/app
COPY --from=build /app/target/app.jar /usr/app/app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/app/app.jar"]